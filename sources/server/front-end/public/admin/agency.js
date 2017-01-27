GET_AGENCY_URL = "http://localhost:9000/agency";
// maps routeShortName to array of stations
var routes = new Map();
// maps markers to stations for easy finding
var markers = new Map();
var routePolyline;

//// HTML controls
agencyNameInput = document.getElementById('agencyName');
agencyCityInput = document.getElementById('agencyCity');
lineIdSelect = document.getElementById('lineIdSelect');
// Line dialog
lineIdInput = document.getElementById('lineId');
lineTypeSelect = document.getElementById('lineTypeSelect');
// Station dialog
stationLineIdLabel = document.getElementById('lineIdLabel');
stationNameInput = document.getElementById('stationName');
stationLatInput = document.getElementById('stationLat');
stationLngInput = document.getElementById('stationLng');

// UI setup
$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
  refreshScreen(lineIdSelect.value, e.target.id);
});
$('#stationInfoDialog').on('shown.bs.modal', function() {
  $('#stationName').focus();
});
$.ajaxSetup({
  contentType: "application/json; charset=utf-8"
});
$('input.agencyData').change(function () {
   postLinesData();
});

//// Initialization

$(document).ready(function() {
    $.getJSON(GET_AGENCY_URL + "/60",
        function(agency){
            agencyNameInput.value = agency.agencyName;
            for (route of agency.routes) {
                routes.set(route.routeShortName, route);
            }
            refreshRouteList(routes);
        }
    );
});

function refreshRouteList(routes) {
    lineIdSelect.innerHTML = "";
    for (key of getSortedArray(routes.keys())) {
        addRouteToList(routes.get(key).routeShortName, routes.get(key).routeType);
    }
}

function getSortedArray(iterator) {
    var a = [];
    for (e of iterator) {
        a.push(e);
    }
    return a.sort(cmpStringsWithNumbers);
}

// Called by Google Maps API on initialization
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

//// Event handlers

function onAddNewLine() {
    $('#lineInfoDialog').modal('show');
    $('#lineInfoForm').unbind('submit').bind('submit', function(event){
        // prevent default browser behaviour
        event.preventDefault();
        onSaveLine();
    });
    lineIdInput.value = "";
    lineTypeSelect.selectedIndex = 0;
}

function onSaveLine(lineId) {
    routeShortName = lineIdInput.value;
    routeType = lineTypeSelect.value;
    if (routeShortName == "") {
        $(lineIdInput).closest('.input-group').addClass('has-error');
        return;
    }
    if (!lineId) { // new line
        routes.set(routeShortName, {
            routeShortName: routeShortName,
            routeLongName: "Linia " + routeShortName,
            routeColor: "blue",
            routeDesc: "string123",
            routeTextColor: "black",
            routeType: routeType,
            routeUrl: "string"
        });
        refreshRouteList(routes);
        lineIdSelect.value = routeShortName;
    } else {
        // edit
    }
    // close
    $('#lineInfoDialog').modal('hide');
    postLinesData();
}

// Adds (and formats) a single option to the list of routes
function addRouteToList(routeShortName, routeType) {
    var option = document.createElement("option");
    option.value = routeShortName;
    option.text = routeShortName + "(" + routeType + ")";
    lineIdSelect.add(option);
}

// Handler for the line select box
function onLineChange(selected) {
    refreshScreen(selected.value);
}

function onMapClick(map, position) {
    lineId = lineIdSelect.value;
    marker = new google.maps.Marker({
        position: position,
        draggable: true,
        map: map,
    });
    showStationDialog(lineId, null, marker);
}

function onMarkerClick(map, marker) {
    var station = markers.get(marker);
    var lineId = lineIdSelect.value;
    showStationDialog(lineId, station.id, marker);
//    currentMarker = marker;
}

function showStationDialog(lineId, stationId, marker) {
    $('#stationInfoDialog').modal('show');
    $('#stationForm').unbind('submit').bind('submit', function(event){
        // prevent default browser behaviour
        event.preventDefault();
        onSaveStation(lineId, stationId, marker);
    });
    $('#stationInfoDialog').on('hidden.bs.modal', function () {
        cancelDialog(marker);
    });

    stationLineIdLabel.innerHTML = lineIdSelect.value;
    station = getStationById(stationName);
    stationNameInput.value = station.name;
    if (!marker) {
        marker = station.marker;
    }
    stationLatInput.value = marker.getPosition().lat();
    stationLngInput.value = marker.getPosition().lng();
}

function onSaveStation(lineId, stationId, marker) {
    stationName = stationNameInput.value;
    if (!stationId) { // this is a new station
        marker.setTitle(stationName);
        // TODO check why position is set to undefined
//        position = new google.maps.LatLng(Number(stationLatInput.value), Number(stationLngInput.value));
//        marker.setPosition();
        google.maps.event.addListener(marker, 'click', function() {
            onMarkerClick(map, marker);
        });
        google.maps.event.addListener(marker, 'dragend', function() {
            drawRoute(lineId);
        });
        stationId = "S" + markers.size;

// TODO only for development
if (!stationName) { stationName = stationId;}

        var station = {
            id: stationId,
            name: stationName,
            marker: marker,
            routes: []
        };
        setStationDescription(station);
        markers.set(marker, station);
        if (lineId) {
            station.routes.push(lineId);
            if (routes.get(lineId).stations) { // add station to line
                routes.get(lineId).stations.push(station);
            } else { // first station on this line
                routes.set(lineId, [station]);
            }
        } else { // free station

        }
    } else { // modify existing station
        station = markers.get(marker);
        station.name = stationName;
        if (!station.routes.includes(lineId)) {
            station.routes.push(lineId);
        }
        setStationDescription(station);
    }
    // close
    $('#stationInfoDialog').modal('hide');
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
    stations = routes.get(lineId).stations;
    if (lineId && stations) {
        drawRoute(lineId);
        displayStations(lineId);
    }
}

function displayStations(lineId) {
    stationListDiv = document.getElementById('stationList');
    var optionsHtml = "";
    for (var station of routes.get(lineId)) {
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
    for (var station of stations) {
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

//// REST API

function postLinesData() {
    agency = {
        agencyId: document.getElementById("agencyId").value,
        agencyLang: "RO",
        agencyName: document.getElementById("agencyName").value,
        agencyPhone: "string",
        agencyTimezone: "GMT+2",
        agencyUrl: "string",
        fareUrl: "string",
        routes: Array.from(routes.values())
    }
    $.post(GET_AGENCY_URL, JSON.stringify(agency));
}

// Helpers

function setStationDescription(station) {
    var lineString = '';
    for (var lineId of station.routes) {
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

(function() {
  // Regular expression to separate the digit string from the non-digit strings.
  var reParts = /\d+|\D+/g;

  // Regular expression to test if the string has a digit.
  var reDigit = /\d/;

  // Add cmpStringsWithNumbers to the global namespace.  This function takes to
  // strings and compares them, returning -1 if `a` comes before `b`, 0 if `a`
  // and `b` are equal, and 1 if `a` comes after `b`.
  cmpStringsWithNumbers = function(a, b) {
    // Get rid of casing issues.
    a = a.toUpperCase();
    b = b.toUpperCase();

    // Separates the strings into substrings that have only digits and those
    // that have no digits.
    var aParts = a.match(reParts);
    var bParts = b.match(reParts);

    // Used to determine if aPart and bPart are digits.
    var isDigitPart;

    // If `a` and `b` are strings with substring parts that match...
    if(aParts && bParts &&
        (isDigitPart = reDigit.test(aParts[0])) == reDigit.test(bParts[0])) {
      // Loop through each substring part to compare the overall strings.
      var len = Math.min(aParts.length, bParts.length);
      for(var i = 0; i < len; i++) {
        var aPart = aParts[i];
        var bPart = bParts[i];

        // If comparing digits, convert them to numbers (assuming base 10).
        if(isDigitPart) {
          aPart = parseInt(aPart, 10);
          bPart = parseInt(bPart, 10);
        }

        // If the substrings aren't equal, return either -1 or 1.
        if(aPart != bPart) {
          return aPart < bPart ? -1 : 1;
        }

        // Toggle the value of isDigitPart since the parts will alternate.
        isDigitPart = !isDigitPart;
      }
    }

    // Use normal comparison.
    return (a >= b) - (a <= b);
  };
})();
