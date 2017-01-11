var lines = new Map();
var markers = new Map();
var currentMarker;
var routePolyline;

// elements
lineIdInput = document.getElementById('lineIdInput');
lineIdLabel = document.getElementById('lineIdLabel');
stationNameInput = document.getElementById('stationName');
stationLatInput = document.getElementById('stationLat');
stationLngInput = document.getElementById('stationLng');

function loadMap() {
    var mapCanvas = document.getElementById("map");
    var mapOptions = {
    center: new google.maps.LatLng(document.getElementById("agencyAreaLat").value,
                document.getElementById("agencyAreaLng").value),
    zoom: 14
    }
    map = new google.maps.Map(mapCanvas, mapOptions);
    google.maps.event.addListener(map, 'click', function(event) {
        onMapClick(map, event.latLng);
    });
    routePolyline = new google.maps.Polyline();
    routePolyline.setMap(map);
}

function onLineChange() {
    drawRoute();
}

function onMapClick(map, position) {
    currentMarker = new google.maps.Marker({
        position: position,
        draggable: true,
        map: map,
    });
    newStationName = "S" + (markers.size + 1);
    showStationDialog(position, newStationName);
}

function onMarkerClick(map, marker) {
    var station = markers.get(marker);
    showStationDialog(marker.getPosition(), station.name);
    currentMarker = marker;
}

function showStationDialog(position, stationName) {
    $('#lineInfoDialog').modal('show');
    $('#lineInfoDialog').on('hidden.bs.modal', function (e) {
        cancelDialog();
    });
    lineIdLabel.innerHTML = lineIdInput.value;
    stationNameInput.value = stationName;
    stationLatInput.value = position.lat();
    stationLngInput.value = position.lng();
}

function saveLineButtonClicked() {
    lineId = lineIdInput.value;
    stationName = stationNameInput.value;
    if (!markers.get(currentMarker)) { // this is a new station
        currentMarker.setTitle(stationName);
        // TODO check why position is set to undefined
//        position = new google.maps.LatLng(Number(stationLatInput.value), Number(stationLngInput.value));
//        currentMarker.setPosition();
        var marker = currentMarker;
        google.maps.event.addListener(marker, 'click', function() {
            onMarkerClick(map, marker);
        });
        google.maps.event.addListener(currentMarker, 'dragend', function() {
            drawRoute();
        });
        var station = {
            name: stationName,
            marker: currentMarker,
            lines: [lineId]
        };
        setStationDescription(station);
        markers.set(currentMarker, station);
    } else { // modify existing station
        station = markers.get(currentMarker);
        station.name = stationName;
        if (!station.lines.includes(lineId)) {
            station.lines.push(lineId);
        }
        setStationDescription(station);
    }
    if (lines.get(lineId)) { // add station to line
        lines.get(lineId).push(station);
    } else { // first station on this line
        lines.set(lineId, [station]);
    }
    // close
    showInfoWindow(map, currentMarker);
    drawRoute();
}

function setStationDescription(station) {
    var lineString = '';
    for (var lineId of station.lines) {
        lineString += lineId + ' ';
    }
    station.description = 'Stația ' + station.name + '<br>Linia ' + lineString;
}

function cancelDialog() {
    if (!markers.get(currentMarker)) {
        currentMarker.setMap(null);
    }
}

function drawRoute() {
    lineId = lineIdInput.value;
    if (lines.get(lineId)) {
        var path = [];
        for (var station of lines.get(lineId)) {
            path.push(station.marker.getPosition());
        }
        routePolyline.setOptions({
            path: path,
            strokeColor: "#0000FF",
            strokeOpacity: 0.5,
            strokeWeight: 4,
            editable: true
        });
    }
}

function showInfoWindow(map, marker) {
    var info = new google.maps.InfoWindow({
        content: markers.get(marker).description
    });
    info.open(map, marker);
}