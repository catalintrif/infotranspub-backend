//STOPS_URL = "/stops";
// TODO for development
STOPS_URL = "stops.json";
// list of {value: stationId, label: stationName} pairs
var stations = [];
// station IDs of the currently selected stations
var stationFromId, stationToId;
// controls
var stationFromInput = document.getElementById("stationFrom");
var stationToInput = document.getElementById("stationTo");

$(document).ready(function() {
    // load all stations at once
    $.getJSON(STOPS_URL,
        function(stops){
            for (var stop of stops) {
                stations.push({
                    value: stop.stopId,
                    label: stop.stopName
                });
            }
        }
    );
});

$(".autocomplete").autocomplete({
    source: stations,
    select: function( event, ui ) {
        event.target.value = ui.item.label;
        console.log("Selected " + event.target.id + "=" + ui.item.value + "." + ui.item.label);
        selectStation(event, ui);
        return false;
    }
});

function selectStation(event, ui) {
    if (event.target.id == stationFromInput.id) {
        stationFromId = ui.item.value;
    } else {
        stationToId = ui.item.value;
    }
    displayResults();
}

function displayResults() {
    // TODO scroll down first
    if (stationFromId && stationToId) {
        console.log("getResuls " + stationFromId + " > " + stationToId)
    }
}

function onSwitchStations() {
    syncStationNameWithId();
    tempId = stationToId;
    stationToId = stationFromId;
    stationFromId = tempId;
    tempName = stationToInput.value;
    stationToInput.value = stationFromInput.value;
    stationFromInput.value = tempName;
    displayResults();
}

function syncStationNameWithId() {
    if (stationFromInput.value == "") {
        stationFromId = null;
    }
    if (stationToInput.value == "") {
        stationToId = null;
    }
}
