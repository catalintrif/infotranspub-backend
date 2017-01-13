// maps line IDs to arrays of stations
var lines = new Map();
// maps markers to stations for easy finding
var markers = new Map();
var routePolyline;

// elements
lineIdInput = document.getElementById('lineIdInput');
lineIdLabel = document.getElementById('lineIdLabel');
stationNameInput = document.getElementById('stationName');
stationLatInput = document.getElementById('stationLat');
stationLngInput = document.getElementById('stationLng');

$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
  refreshScreen(lineIdInput.value, e.target.id);
})
$('#lineInfoDialog').on('shown.bs.modal', function() {
  $('#stationName').focus();
})

// Event handlers

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

function onLineChange(selected) {
    refreshScreen(selected.value);
}

function onMapClick(map, position) {
    marker = new google.maps.Marker({
        position: position,
        draggable: true,
        map: map,
    });
    lineId = lineIdInput.value;
    showStationDialog(lineId, null, marker);
}

function onMarkerClick(map, marker, lineId) {
    var station = markers.get(marker);
    showStationDialog(lineId, station.id, marker);
//    currentMarker = marker;
}

function showStationDialog(lineId, stationId, marker) {
    $('#lineInfoDialog').modal('show');
    $('#stationForm').unbind('submit').bind('submit', function(event, lineId){
        // prevent default browser behaviour
        event.preventDefault();
        saveLineButtonClicked(lineId, stationId, marker);
    });
    $('#lineInfoDialog').on('hidden.bs.modal', function () {
        cancelDialog(marker);
    });

    lineIdLabel.innerHTML = lineIdInput.value;
    if (!marker) {
        station = getStationById(stationName);
        marker = station.marker;
    }
    stationLatInput.value = marker.getPosition().lat();
    stationLngInput.value = marker.getPosition().lng();
}

function saveLineButtonClicked(lineId, stationId, marker) {
    // TODO check why this is passed undefined
    if (!lineId) {
        lineId = lineIdInput.value;
    }
    stationName = stationNameInput.value;
    if (!stationId) { // this is a new station
        marker.setTitle(stationName);
        // TODO check why position is set to undefined
//        position = new google.maps.LatLng(Number(stationLatInput.value), Number(stationLngInput.value));
//        marker.setPosition();
        google.maps.event.addListener(marker, 'click', function() {
            onMarkerClick(map, marker, lineId);
        });
        google.maps.event.addListener(marker, 'dragend', function() {
            drawRoute(lineId);
        });
        stationId = "S" + markers.size;

    // TODO only for development
    if (!stationName) {
        stationName = stationId;
    }

        var station = {
            id: stationId,
            name: stationName,
            marker: marker,
            lines: [lineId]
        };
        setStationDescription(station);
        markers.set(marker, station);
        if (lines.get(lineId)) { // add station to line
            lines.get(lineId).push(station);
        } else { // first station on this line
            lines.set(lineId, [station]);
        }
    } else { // modify existing station
        station = markers.get(marker);
        station.name = stationName;
        if (!station.lines.includes(lineId)) {
            station.lines.push(lineId);
        }
        setStationDescription(station);
    }
    // close
    $('#lineInfoDialog').modal('hide');
    showInfoWindow(map, marker);
    refreshScreen(lineId);
}

function cancelDialog(marker) {
    if (!markers.get(marker)) {
        marker.setMap(null);
    }
}

// UI related

function refreshScreen(lineId) {
    drawRoute(lineId);
    displayStations(lineId);
}

function displayStations(lineId) {
    stationListDiv = document.getElementById('stationList');
    var optionsHtml = '';
    for (var station of lines.get(lineId)) {
        optionsHtml +=
            '<button type="button" class="list-group-item"' +
            '   onclick="showStationDialog(' +
                + lineId + ', \''+ station.name +'\', '+ null +')">' +
            '    <span class="label label-warning">&nbsp;</span> ' + station.name +
            '</button>';
    }
    stationListDiv = document.getElementById('stationList');
    stationListDiv.innerHTML = optionsHtml;
}

function drawRoute(lineId) {
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

function showInfoWindow(map, marker) {
    var info = new google.maps.InfoWindow({
        content: markers.get(marker).description
    });
    info.open(map, marker);
}

// Data related

function setStationDescription(station) {
    var lineString = '';
    for (var lineId of station.lines) {
        lineString += lineId + ' ';
    }
    station.description = 'Stația ' + station.name + '<br>Linia ' + lineString;
}

function getStationById(id) {
    for (var station of markers.values()) {
        if (station.name == id) {
            return station;
        }
    }
}
