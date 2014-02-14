function initialize(runningtask) {
    if (runningtask == "gathering") {
        $("#datagatherform").remove();
        $("#dgheaderchange").text("Data Gathering is currently ongoing...");
        $("#dgcolumn").append("<div class='ui active striped progress' id='dgprogress'><div class='bar' style='width: 100%;'></div></div>");
        $("#trainingform").remove();
        $("#theaderchange").text("Data Gathering is currently ongoing...");
        $("#tcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Data Gathering Phase.</p></div>");
        $("#productionform").remove();
        $("#pheaderchange").text("Data Gathering is currently ongoing...");
        $("#pcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Data Gathering Phase.</p></div>");
        $.get('ServletGathering', function(responseText) {
            $('#dgprogress').append("<p id='dginstance'>" + responseText + "</p>");
        });
        setInterval(function() {
            if (true) {
                $.get('ServletGathering', function(responseText) {
                    $('#dginstance').text(responseText);
                });
                i++;
            }
        }, 500);
        $("#dgcolumn").append("<div class='ui form'><div style='margin-top:50px;'><a class='ui red submit button' onclick='submitDataGatheringFormStop()'>Stop</a></div>");
    } else if (runningtask == "training") {
        $("#datagatherform").remove();
        $("#dgheaderchange").text("Training is currently ongoing...");
        $("#dgcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Training Phase.</p></div>");
        $("#trainingform").remove();
        $("#theaderchange").text("Training is currently ongoing...");
        $("#tcolumn").append("<div class='ui active striped progress' id='tprogress'><div class='bar' style='width: 100%;'></div></div>");
        $("#productionform").remove();
        $("#pheaderchange").text("Training is currently ongoing...");
        $("#pcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Training Phase.</p></div>");
        $.get('ServletTraining', function(responseText) {
            $('#tprogress').append("<p id='tresponse'>" + responseText + "</p>");
        });
        var timer = setInterval(function() {
            if (true) {
                $.get('ServletTraining', function(responseText) {
                    $('#tresponse').text(responseText);
                    if(responseText.search("100%") != -1){
                        clearInterval(timer);
                        $('#tfinishdimmer').dimmer('toggle');
                        
                    }
                });
                i++;
            }
        }, 500);
        $("#tcolumn").append("<div class='ui form'><div style='margin-top:50px;'><a class='ui red submit button' onclick='submitTrainingFormStop()' id='tstop'>Stop</a></div>");
    } else if (runningtask == "production") {
        $("#datagatherform").remove();
        $("#dgheaderchange").text("Production is currently ongoing...");
        $("#dgcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Production Phase.</p></div>");
        $("#trainingform").remove();
        $("#theaderchange").text("Production is currently ongoing...");
        $("#pcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Production Phase.</p></div>");
        $("#productionform").remove();
        $("#pheaderchange").text("Production is currently ongoing...");
        $("#pcolumn").append("<div class='ui active striped progress' id='tprogress'><div class='bar' style='width: 100%;'></div></div>");
        $("#pcolumn").append("<div class='ui form'><div style='margin-top:20px;'><a class='ui red submit button' onclick='submitProductionFormStop()'>Stop</a></div>");
    } else if (runningtask == "none") {
    }
}

function submitDataGatheringFormStop() {
    var formData = {
        action: 'stop'
    };

    $.ajax({
        type: 'POST',
        url: ServletGathering,
        data: formData,
        success: function() {
            window.location.reload(true);
        }
    });
}

function submitDataGatheringForm() {
    var formData = {
        interface: $('#dginterface').val(),
        packetfilter: ($('#dgenablepacketfilter').val() == 'on') ? $('#dgpacketfilter').val() : null,
        packetfilterswitch: $('#dgpacketfilterswitch').val(),
        trainingfilter: ($('#dgenabletrainingfilter').val() == 'on') ? $('#dgtrainingfilter').val() : null,
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

function onDataGatheringFormSubmitted(response) {
    $('#dgstartdimmer').dimmer('toggle');
    $("#datagatherform").remove();
    $("#dgheaderchange").text("Data Gathering is currently ongoing...");
    $("#dgcolumn").append("<div class='ui active striped progress' id='dgprogress'><div class='bar' style='width: 100%;'></div></div>");
    $("#trainingform").remove();
    $("#theaderchange").text("Data Gathering is currently ongoing...");
    $("#tcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Data Gathering Phase.</p></div>");
    $("#productionform").remove();
    $("#pheaderchange").text("Data Gathering is currently ongoing...");
    $("#pcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Data Gathering Phase.</p></div>");
    $.get('ServletGathering', function(responseText) {
        $('#dgprogress').append("<p id='dginstance'>" + responseText + "</p>");
    });
    setInterval(function() {
        if (true) {
            $.get('ServletGathering', function(responseText) {
                $('#dginstance').text(responseText);
            });
            i++;
        }
    }, 500);
    $("#dgcolumn").append("<div class='ui form'><div style='margin-top:50px;'><a class='ui red submit button' onclick='submitDataGatheringFormStop()'>Stop</a></div>");
}

function submitTrainingFormStop() {
    var formData = {
        action: 'stop'
    };

    $.ajax({
        type: 'POST',
        url: ServletTraining,
        data: formData,
        success: function() {
            window.location.reload(true);
        }
    });
}

function submitTrainingForm() {
    var formData = {
        trainingfile: $('#ttrainingfile').val(),
        filter: ($('#tenablefilter').val() == 'on') ? $('#tfilter').val() : null,
        exclude: $('#texclude').val(),
        action: 'start'
    };

    $.ajax({
        type: 'POST',
        url: 'ServletTraining',
        data: formData,
        success: onTrainingFormSubmitted
    });
}

function onTrainingFormSubmitted(response) {
    $('#tstartdimmer').dimmer('toggle');
    $("#datagatherform").remove();
    $("#dgheaderchange").text("Training is currently ongoing...");
    $("#dgcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Training Phase.</p></div>");
    $("#trainingform").remove();
    $("#theaderchange").text("Training is currently ongoing...");
    $("#tcolumn").append("<div class='ui active striped progress' id='tprogress'><div class='bar' style='width: 100%;'></div></div>");
    $("#productionform").remove();
    $("#pheaderchange").text("Training is currently ongoing...");
    $("#pcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Training Phase.</p></div>");
    $.get('ServletTraining', function(responseText) {
        $('#tprogress').append("<p id='tresponse'>" + responseText + "</p>");
    });
    setInterval(function() {
        if (true) {
            $.get('ServletTraining', function(responseText) {
                $('#tresponse').text(responseText);
            });
            i++;
        }
    }, 500);
    $("#tcolumn").append("<div class='ui form'><div style='margin-top:50px;'><a class='ui red submit button' onclick='submitTrainingFormStop()'>Stop</a></div>");
}

function submitProductionFormStop() {
    var formData = {
        action: 'stop'
    };

    $.ajax({
        type: 'POST',
        url: ServletProduction,
        data: formData,
        success: function() {
            window.location.reload(true);
        }
    });
}

function submitProductionForm() {
    var formData = {
        modelfile: $('#pmodelfile').val(),
        syslog: $('#psyslog').val(),
        syslogport: $('#psyslogport').val(),
        firewall: $('#pfirewall').val(),
        action: 'start'
    };

    $.ajax({
        type: 'POST',
        url: 'ServletProduction',
        data: formData,
        success: onProductionFormSubmitted
    });
}

// Handle post response
function onProductionFormSubmitted(response) {
    $('#pstartdimmer').dimmer('toggle');
    $("#datagatherform").remove();
    $("#dgheaderchange").text("Production is currently ongoing...");
    $("#dgcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Production Phase.</p></div>");
    $("#trainingform").remove();
    $("#theaderchange").text("Production is currently ongoing...");
    $("#pcolumn").append("<div class='ui error icon message'><i class='exclamation icon'></i><div class='header'>System is busy!</div><p>The system is running Production Phase.</p></div>");
    $("#productionform").remove();
    $("#pheaderchange").text("Production is currently ongoing...");
    $("#pcolumn").append("<div class='ui active striped progress' id='tprogress'><div class='bar' style='width: 100%;'></div></div>");
    $("#pcolumn").append("<div class='ui form'><div style='margin-top:20px;'><a class='ui red submit button' onclick='submitProductionFormStop()'>Stop</a></div>");
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

// Handle post response
function onConfigurationFormSubmitted(response) {
    $('#cstartdimmer').dimmer('toggle');
}

$(document).ready(
        function() {
            $('.message .close').on('click', function() {
                $(this).closest('.message').fadeOut();
            });

            $('.ui.checkbox')
                    .checkbox()
                    ;
            $('.ui.dropdown')
                    .dropdown()
                    ;

            $('#adduser').on('click', function() {
                $('.ui.sidebar')
                        .sidebar({
                            overlay: true
                        })
                        .sidebar('toggle')
                        ;
            });

            $('#dshowoutputmessages').on('click', function() {
                $('#dside')
                        .sidebar({
                            overlay: true
                        })
                        .sidebar('toggle')
                        ;
            });

            $('#newuser').on('click', function() {
                $('#uside')
                        .sidebar({
                            overlay: true
                        })
                        .sidebar('toggle')
                        ;
            });

            $(".sticky").sticky({
                topSpacing: 30
            });
        });
