function debugObject(parsed) {
    var output = '';
    for (var property in parsed) {
        output += property + ': ' + parsed[property] + '; ';
    }
    return output;
}

function pageReload() {
    window.location.reload(true);
}

function initialize(runningtask) {
    if (runningtask === "gathering") {
        gatheringUI();
    } else if (runningtask === "training") {
        trainingUI();
    } else if (runningtask === "production") {
        productionUI();
    }
    else if (runningtask === "none") {}
}

function submitDataGatheringFormStopNoReload() {
    var formData = {
        action: 'stop'
    };
    $.ajax({
        type: 'POST',
        url: 'ServletGathering',
        async: false,
        data: formData,
        success: function(outputfile) {
            var filelocation = outputfile;
            filelocation = filelocation.replace(/\\/g, '/');
            $("#dgstop").remove();
            //$("#dgcolumn").append("<a class='ui blue button' style='text-decoration:none;color:white;margin-top:50px;' href='file:///" + filelocation + "'><i class='icon download'></i>Download</a>");
            $('#dgstopdimmer').dimmer('toggle');
        }
    });
}

function submitTrainingFormStopNoReload() {
    var formData = {
        action: 'stop'
    };
    $.ajax({
        type: 'POST',
        url: 'ServletTraining',
        async: false,
        data: formData,
        success: function(outputfile) {
            var filelocation = outputfile;
            filelocation = filelocation.replace(/\\/g, '/');
            $('#tresponse').text('[100%] - Model file is now available');
            $("#theaderchange").text("Training completed successfully!");
            $("#tstop").remove();
            //$("#tcolumn").append("<a class='ui blue button' style='text-decoration:none;color:white;margin-top:50px;' href='file:///" + filelocation + "' onClick='submitTrainingFormStop();'><i class='icon download'></i>Download</a>");
            $('#tfinishdimmer').dimmer('toggle');
        }
    });
}

function submitProductionFormStop() {
    var formData = {
        action: 'stop'
    };
    $.ajax({
        type: 'POST',
        url: 'ServletProduction',
        async: false,
        data: formData,
        success: function() {
            pageReload();
        }
    });
}

function submitDataGatheringForm() {
    var formData = {
        interface: $('#dginterface').val(),
        packetfilter: ($('#dgenablepacketfilter').val() === 'on') ? $('#dgpacketfilter').val() : null,
        packetfilterswitch: $('#dgpacketfilterswitch').val(),
        trainingfilter: ($('#dgenabletrainingfilter').val() === 'on') ? $('#dgtrainingfilter').val() : null,
        attackswitch: $('#dgattackswitch').val(),
        action: 'start'
    };
    $.ajax({
        type: 'POST',
        url: 'ServletGathering',
        data: formData,
        success: onDataGatheringFormSubmitted
    });
}

function gatheringUI() {
    $("#datagatherform").remove();
    $("#dgheaderchange").text("Data Gathering is currently ongoing...");
    $("#dgcolumn").append("<div class='ui active striped progress' id='dgprogress'><div class='bar' style='width: 100%;'></div></div>");
    $("#trainingform").remove();
    $("#theaderchange").text("Data Gathering is currently ongoing...");
    $("#tcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Data Gathering Phase.</p></div>");
    $("#productionform").remove();
    $("#pheaderchange").text("Data Gathering is currently ongoing...");
    $("#pcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Data Gathering Phase.</p></div>");
    $.get('ServletGathering', {
        action: 'state'
    }, function(responseText) {
        $('#dgprogress').append("<p id='dginstance'>" + responseText + "</p>");
    }, 'html');
    setInterval(function() {
        $.get('ServletGathering', {
            action: 'state'
        }, function(responseText) {
            $('#dginstance').text(responseText);
        });
    }, 500);
    $("#dgcolumn").append("<a class='ui red submit button' onClick='submitDataGatheringFormStopNoReload();' id='dgstop' style='margin-top:50px;'><i class='icon stop'></i>Stop</a>");
}

function trainingUI() {
    $("#datagatherform").remove();
    $("#dgheaderchange").text("Training is currently ongoing...");
    $("#dgcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Training Phase.</p></div>");
    $("#trainingform").remove();
    $("#theaderchange").text("Training is currently ongoing...");
    $("#tcolumn").append("<div class='ui active striped progress' id='tprogress'><div class='bar' style='width: 100%;'></div></div>");
    $("#productionform").remove();
    $("#pheaderchange").text("Training is currently ongoing...");
    $("#pcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Training Phase.</p></div>");
    $.get('ServletTraining', {
        action: 'state'
    }, function(responseText) {
        $('#tprogress').append("<p id='tresponse'>" + responseText + "</p>");
    }, 'html');
    var timer = setInterval(function() {
        $.get('ServletTraining', {
            action: 'state'
        }, function(responseText) {
            var text = responseText;
            var regExp = /\[([^)]+)\]/;
            var matches = regExp.exec(text);

            $("#tprogress .bar").css("width", matches[1]);
            $('#tresponse').text(text);

            if (matches[1] === '100%') {
                clearInterval(timer);
                submitTrainingFormStopNoReload();
            }
        }, 'html');
    }, 500);
    //$("#tcolumn").append("<a class='ui red submit button' onclick='submitTrainingFormStop()' id='tstop' style='margin-top:50px;'><i class='icon stop'></i>Stop</a>");
}

function productionUI() {
    $("#datagatherform").remove();
    $("#dgheaderchange").text("Production is currently ongoing...");
    $("#dgcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Production Phase.</p></div>");
    $("#trainingform").remove();
    $("#theaderchange").text("Production is currently ongoing...");
    $("#tcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Production Phase.</p></div>");
    $("#productionform").remove();
    $("#pheaderchange").text("Production is currently ongoing...");
    $("#pcolumn").append("<div class='ui active striped progress' id='tprogress'><div class='bar' style='width: 100%;'></div></div>");
    //$("#pcolumn").append("<a class='ui red submit button' onclick='submitProductionFormStop()' id='pstop' style='margin-top:20px;'><i class='icon stop'></i>Stop</a>");
}

function submitConfigurationForm() {
    var formData = {
        tcptimeout: $('#ctcptimeout').val(),
        criteriatimeout: $('#ccriteriatimeout').val(),
        controlport: $('#ccontrolport').val(),
        interface: $('#cinterface').val(),
        syslogport: $('#csyslogport').val(),
        action: 'start'
    };
    $.ajax({
        type: 'POST',
        url: 'ServletConfig',
        data: formData,
        success: onConfigurationFormSubmitted
    });
}

function onConfigurationFormSubmitted(response) {
    $('#cstartdimmer').dimmer('toggle');
}

$(document).ready(
        function() {
            $('.ui.checkbox').checkbox();
            $('.ui.dropdown').dropdown();

            $('.message .close').on('click', function() {
                $(this).closest('.message').fadeOut();
            });

            $('#adduser').on('click', function() {
                $('.ui.sidebar').sidebar({
                    overlay: true
                }).sidebar('toggle');
            });

            $('#dshowoutputmessages').on('click', function() {
                $('#dside').sidebar({
                    overlay: true
                }).sidebar('toggle');
            });

            $('#newuser').on('click', function() {
                $('#uside').sidebar({
                    overlay: true
                }).sidebar('toggle');
            });

            $(".sticky").sticky({
                topSpacing: 30
            });
        });
