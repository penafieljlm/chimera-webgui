function initialize() {
    $.get('DataGathering', function(responseText) { 
        if(responseText != "No task is running"){
            $("#datagatherform").remove();
            $("#dgheaderchange").text("Data Gathering is currently ongoing...");
            $("#dgcolumn").append("<div class='ui active striped progress' id='dgprogress'><div class='bar' style='width: 100%;'></div></div>");

            $.get('DataGathering', function(responseText) { 
                $('#dgprogress').append("<p id='dginstance'>Number of instances gathered: " + responseText + "</p>");         
            });
            setInterval(function() { 
                if (true) {
                    $.get('DataGathering', function(responseText) { 
                        $('#dginstance').text("Number of instances gathered: " + responseText);        
                    });
                    i++;
                }
            }, 500);
            $("#dgcolumn").append("<div class='ui form' id='datagatherformstop' action='DataGathering' method='post'><div style='margin-top:50px;'><a class='ui red submit button' onclick='submitDataGatheringFormStop()' name='action' value='stop'>Stop</a></div>");
        }
    });
}

function submitDataGatheringFormStop() {
    var formData = {
        action: 'stop'
    };

    $.ajax({
        type: 'POST',
        url: 'DataGathering',
        data: formData,
        success: function() {
            window.location.reload(true);
        }
    });
}

function submitDataGatheringForm() {
    var formData = {
        outputfile: $('#dgoutputfile').val(),
        interface: $('#dginterface').val(),
        packetfilter: $('#dgpacketfilter').val(),
        packetfilterswitch: $('#dgpacketfilterswitch').val(),
        trainingfilter: $('#dgtrainingfilter').val(),
        attackswitch: $('#dgattackswitch').val(),
        action: 'start'
    };

    $.ajax({
        type: 'POST',
        url: 'DataGathering',
        data: formData,
        success: onDataGatheringFormSubmitted
    });
}

function onDataGatheringFormSubmitted(response) {
    $('#dgstartdimmer').dimmer('toggle');
    $("#datagatherform").remove();
    $("#dgheaderchange").text("Data Gathering is currently ongoing...");
    $("#dgcolumn").append("<div class='ui active striped progress' id='dgprogress'><div class='bar' style='width: 100%;'></div></div>");

    $.get('DataGathering', function(responseText) { 
        $('#dgprogress').append("<p id='dginstance'>Number of instances gathered: " + responseText + "</p>");         
    });
    setInterval(function() { 
        if (true) {
            $.get('DataGathering', function(responseText) { 
                $('#dginstance').text("Number of instances gathered: " + responseText);        
            });
            i++;
        }
    }, 500);
    $("#dgcolumn").append("<div class='ui form' id='datagatherformstop' action='DataGathering' method='post'><div style='margin-top:50px;'><a class='ui red submit button' onclick='submitDataGatheringFormStop()' name='action' value='stop'>Stop</a></div>");
}

function submitTrainingForm() {
    var formData = {
        trainingfile: $('#ttrainingfile').val(),
        outputfile: $('#toutputfile').val(),
        filter: $('#tfilter').val(),
        exclude: $('#texclude').val(),
        action: 'start'
    };

    $.ajax({
        type: 'POST',
        url: 'Training',
        data: formData,
        success: onTrainingFormSubmitted
    });
}

function onTrainingFormSubmitted(response) {
    $('#tstartdimmer').dimmer('toggle');
    $("#trainingform").remove();
    $("#tcolumn").append("<div class='ui active striped progress'><div class='bar' style='width: 100%;'></div></div>");
    $("#theaderchange").text("Training is currently ongoing...");
    $("#tcolumn").append("<div class='ui form' id='trainingformstop' action='Training' method='post'><div style='margin-top:20px;'><a class='ui red submit button' name='action' value='stop'>Stop</a></div>");
}

function submitProductionForm() {
    var formData = {
        modelfile: $('#pmodelfile').val(),
        syslog: $('#psyslog').val(),
        firewall: $('#pfirewall').val(),
        action: 'start'
    };

    $.ajax({
        type: 'POST',
        url: 'Production',
        data: formData,
        success: onProductionFormSubmitted
    });
}

// Handle post response
function onProductionFormSubmitted(response) {
    $('#pstartdimmer').dimmer('toggle');
    $("#productionform").remove();
    $("#pheaderchange").text("Production is currently ongoing...");
    $("#pcolumn").append("<div class='ui active striped progress' id='pprogress'><div class='bar' style='width: 100%;'></div></div>");
    $("#pcolumn").append("<div class='ui form' id='productionformstop' action='Production' method='post'><div style='margin-top:20px;'><a class='ui red submit button' name='action' value='stop'>Stop</a></div>");
}

function submitConfigurationForm() {
    var formData = {
        tcptimeout: $('#ctcptimeout').val(),
        criteriatimeout: $('#ccriteriatimeout').val(),
        controlport: $('#ccontrolport').val(),
        interface: $('#cinterface').val(),
        action: 'start'
    };

    $.ajax({
        type: 'POST',
        url: 'Configuration',
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

            $('#adduser').on('click', function() {
                $('.ui.sidebar')
                        .sidebar({
                            overlay: true
                        })
                        .sidebar('toggle')
                        ;
            });

            $('.ui.dropdown')
                    .dropdown()
                    ;

            $('#cshowoutputmessages').on('click', function() {
                $('#cside')
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

            $(".sticky").sticky({topSpacing: 30});


        });
