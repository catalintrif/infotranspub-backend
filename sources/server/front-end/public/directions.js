// TODO change after integration with back-end
//STOPS_URL = "/stops";
STOPS_URL = "stops.json";
// List of stations initially loaded, as list of pairs {value: stationId, label: stationName}
var stations = [];
// station IDs of the currently selected stations
var stationFromId, stationToId;
// value of Leave now dropdown
var delay = 0;

// Controls
stationFromInput = document.getElementById("stationFrom");
stationToInput = document.getElementById("stationTo");
leaveTimeButton = document.getElementById("leaveTimeButton");
errorMessage = document.getElementById("errorMessage");

// Load all stations at once. For a relatively low number of stations (hundreds) this will be faster and will have
// less impact on the server than doing AJAX calls.
$(document).ready(function() {
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

// Configure autocomplete for the 2 station inputs
$(".autocomplete").autocomplete({
    source: stations,
    select: function( event, ui ) {
        event.target.value = ui.item.label;
        console.log("Selected " + event.target.id + "=" + ui.item.value + "." + ui.item.label);
        errorMessage.hidden = true;
        selectStation(event, ui);
        return false;
    },
    focus: function( event, ui ) {
        return false;
    }
});

// Basic validations, getting results from the REST API
function displayResults() {
    // TODO scroll down first
    if (stationFromId && stationToId) {
        if (stationFromId != stationToId) {
            console.log("getResuls " + stationFromId + " > " + stationToId + " delay " + delay);
            // TODO in https://contribute.gov.ro/jira/browse/INFOTRANS-144
        } else {
            errorMessage.hidden = false;
        }
    }
}

//// Handlers

// Changes page text labels based on the selected language.
function onChangeLanguage(language) {
    if (language == "English") {
        changeLabelLanguage(en);
    } else { // default ro
        changeLabelLanguage(ro);
    }
    delay = 0;
}

function onChangeLeaveTime(selectedDelay, languageLink) {
    leaveTimeButton.innerHTML = languageLink.innerHTML;
    delay = selectedDelay;
    displayResults(delay);
}

// Handler for the switch stations button
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

//// Helpers

// Helper for language change
// Labels are defined in maps (en, ro) at the end of this script.
function changeLabelLanguage(lang) {
    labels = document.querySelectorAll("[data-i18n]");
    for (label of labels) {
        label.innerHTML = lang[label.getAttribute("data-i18n")];
    }
    stationFromInput.placeholder = lang["starting_point"];
    stationToInput.placeholder = lang["destination"]
}

function syncStationNameWithId() {
    if (stationFromInput.value == "") {
        stationFromId = null;
    }
    if (stationToInput.value == "") {
        stationToId = null;
    }
}

// Helper for jQuery autocomplete select function
function selectStation(event, ui) {
    if (event.target.id == stationFromInput.id) {
        stationFromId = ui.item.value;
    } else {
        stationToId = ui.item.value;
    }
    displayResults();
}

// Label translations

var ro = {
    "title": "Transport public",
    "directions": "Indicații",
    "lang_button": "English",
    "from": "Din",
    "starting_point": "Plecare",
    "to": "Spre",
    "destination": "Destinație",
    "same_station_warning": "Ați ales aceeași stație.",
    "leave_now": "Pleacă acum",
    "in_10_min": "În 10 minute",
    "in_20_min": "În 20 minute",
    "in_30_min": "În 30 minute",
    "options": "Opțiuni",
    "bus": "Autobuz",
    "metro": "Metrou"
};
var en = {
    "title": "Public transport",
    "directions": "Get Directions",
    "lang_button": "Română",
    "from": "From",
    "starting_point": "Starting point",
    "to": "To",
    "destination": "Destination",
    "same_station_warning": "You selected the same stations.",
    "leave_now": "Leave now",
    "in_10_min": "In 10 minutes",
    "in_20_min": "In 20 minutes",
    "in_30_min": "In 30 minutes",
    "options": "Options",
    "bus": "Bus",
    "metro": "Metro"
};
