<%-- 
    Document   : index
    Created on : 01 14, 14, 10:00:45 PM
    Author     : Emerson Chua
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700|Open+Sans:300italic,400,300,700' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="packaged/css/semantic.css">
        <link rel="stylesheet" type="text/css" href="style.css">

        <script src="jquery.js"></script>
        <script src="jquery.sticky.js"></script>
        <script src="packaged/javascript/semantic.js"></script>
        <script src="http://malsup.github.com/jquery.form.js"></script> 
        <script src="javascript.js"></script>

        <script>
            $(document).ready(function() {
                var rules = {
                    outputfile: {
                        identifier: 'outputfile',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please select an output file'
                            }
                        ]
                    },
                    interface: {
                        identifier: 'interface',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please select a protected interface'
                            }
                        ]
                    }
                };

                var settings = {
                    onSuccess: function() {
                        submitDataGatheringForm();
                    }
                };

                $('#datagatherform').form(rules, settings);
            });
        </script>

        <script>
            $(document).ready(function() {

                var rules = {
                    outputfile: {
                        identifier: 'trainingfile',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please select a training file'
                            },
                            {
                                type: 'contains[.csv]',
                                prompt: 'Training file should be in .csv format'
                            }
                        ]
                    }
                };

                var settings = {
                    onSuccess: function() {
                        /*$('#tstartdimmer').dimmer('toggle');-->*/
                        submitTrainingForm();
                    }
                };

                $('#trainingform').form(rules, settings);
            });
        </script>

        <script>
            $(document).ready(function() {

                var rules = {
                    modelfile: {
                        identifier: 'modelfile',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please select a model file'
                            },
                            {
                                type: 'contains[.cmodel]',
                                prompt: 'Training file should be in .cmodel format'
                            }
                        ]
                    }
                };

                var settings = {
                    onSuccess: function() {
                        submitProductionForm();
                    }
                };

                $('#productionform').form(rules, settings);
            });
        </script>

        <script>
            $(document).ready(function() {

                var rules = {
                };

                var settings = {
                    onSuccess: function() {
                        $('#cstartdimmer').dimmer('toggle');
                    }
                };

                $('#configurationform').form(rules, settings);
            });
        </script>

        <script>
            $(document).ready(function() {

                var rules = {
                    firstName: {
                        identifier: 'first-name',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter your first name'
                            }
                        ]
                    },
                    lastName: {
                        identifier: 'last-name',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter your last name'
                            }
                        ]
                    },
                    username: {
                        identifier: 'username',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter a username'
                            }
                        ]
                    },
                    role: {
                        identifier: 'role',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please select a role'
                            }
                        ]
                    },
                    password: {
                        identifier: 'password',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter a password'
                            },
                            {
                                type: 'length[6]',
                                prompt: 'Your password must be at least 6 characters'
                            }
                        ]
                    },
                    terms: {
                        identifier: 'terms',
                        rules: [
                            {
                                type: 'checked',
                                prompt: 'You must agree to the terms and conditions'
                            }
                        ]
                    }
                };

                var settings = {
                    onSuccess: function() {
                        $('#ustartdimmer').dimmer('toggle');
                    }
                };

                $('#adduserform').form(rules, settings);
            });
        </script>



        <style type="text/css">
            #dgtab{}
            #ttab{display:none;}
            #ptab{display:none;}
            #ctab{display:none;}
            #itab{display:none;}
            #dtab{display:none;}
            #utab{display:none;}
        </style>

        <script type="text/javascript">
            function tab(tab) {
                document.getElementById('dgtab').style.display = 'none';
                document.getElementById('ttab').style.display = 'none';
                document.getElementById('ptab').style.display = 'none';
                document.getElementById('ctab').style.display = 'none';
                document.getElementById('itab').style.display = 'none';
                document.getElementById('dtab').style.display = 'none';
                document.getElementById('utab').style.display = 'none';

                document.getElementById('navdgtab').setAttribute("class", "blue item");
                document.getElementById('navttab').setAttribute("class", "blue item");
                document.getElementById('navptab').setAttribute("class", "blue item");
                document.getElementById('navctab').setAttribute("class", "blue item");
                document.getElementById('navitab').setAttribute("class", "blue item");
                document.getElementById('navdtab').setAttribute("class", "blue item");
                document.getElementById('navutab').setAttribute("class", "blue item");

                document.getElementById(tab).style.display = 'block';

                var d = document.getElementById('nav' + tab);
                d.className = "active " + d.className;
            }
        </script>

    <body>
        <div class="mainheader">
            <div class="tint"></div>

            <div class="minheader">

                <div class="ui labeled icon menu">
                    <a class="red item">
                        <i class="phone icon"></i>
                        Contact
                    </a>
                    <a class="green item">
                        <i class="help icon"></i>
                        Help
                    </a>
                    <a class="teal item">
                        <i class="user icon"></i>
                        fechua
                    </a>
                </div>
                <img id="logo" class="ui image" src="images/chimera2.png">

            </div>
        </div>
        <div class="mainbody">
            <div class="minbody" id="tabs">
                <div class="wrapper">
                    <div class="sticky">
                        <div class="ui secondary vertical pointing menu" id="tabs">
                            <a class="active blue item" onclick="tab('dgtab')" id="navdgtab">
                                <i class="download disk icon"></i> Data Gathering
                            </a>
                            <a class="blue item" onclick="tab('ttab')" id="navttab">
                                <i class="dashboard icon"></i> Training
                            </a>
                            <a class="blue item" onclick="tab('ptab')" id="navptab">
                                <i class="legal icon"></i> Production
                            </a>
                            <a class="blue item" onclick="tab('ctab')" id="navctab">
                                <i class="settings icon"></i> Configuration
                            </a>
                            <a class="blue item" onclick="tab('itab')" id="navitab">
                                <i class="sitemap icon"></i> Interfaces
                            </a>
                            <a class="blue item" onclick="tab('dtab')" id="navdtab">
                                <i class="wrench icon"></i> Diagnosis
                            </a>
                            <a class="blue item" onclick="tab('utab')" id="navutab">
                                <i class="users icon"></i> Users
                            </a>
                        </div>
                    </div>
                </div>

                <div id="Content_Area">

                    <div id="dgtab">
                        <h2 class="ui dividing header">Data Gathering</h2>
                        <div class="ui grid">
                            <div class="column" id="dgcolumn">
                                <h4 class="ui header">About the Data Gathering</h4>
                                <p>The Data Gathering Phase includes packet capture and analysis of attacks which is already a preliminary work before implementing and running the system. It involves capturing the traffic flowing towards the DMZ and vice versa. CHIMERA places sniffed traffic onto a Packet Dump File and forwards packets out the appropriate interface.<br>
                                    <br>The cgather command starts the CHIMERA's Data Gathering phase.
                                    <br>The training set used in the Training Phase is compiled in this phase.
                                    <br>This phase produces the said training set using the traffic captured.
                                    <br>Training set produced is stored on a .csv file.
                                </p>


                                <div class="ui section divider"></div>

                                <h4 class="ui header" id="dgheaderchange">Data Gathering Settings</h4>
                                <div id="dghidden"></div>

                                <div class="ui form" id="datagatherform" action="DataGathering" method="post">
                                    <div class="ui error message"></div>

                                    <div class="field">
                                        <div class="ui fluid labeled input">
                                            <div class="ui corner label">
                                                <i class="icon asterisk"></i>
                                            </div>
                                            <input type="text" placeholder="Output file name..." name="outputfile" id="dgoutputfile">
                                        </div>
                                    </div>

                                    <div style="margin-top:20px;" class="ui selection dropdown">
                                        <input name="interface" id="dginterface" type="hidden">
                                        <label>Protected Interface:</label>
                                        <div class="text">Select</div>
                                        <i class="dropdown icon"></i>
                                        <div class="menu" id="dgdropdown">
                                            <%
                                                int i = 0;
                                                while ((String) request.getAttribute("iface" + i) != null) {
                                            %>
                                            <div class="item" value="<%=(String) request.getAttribute("iface" + i)%>"><%=(String) request.getAttribute("iface" + i)%></div>
                                            <%
                                                    i++;
                                                }
                                            %>
                                        </div>
                                    </div>

                                    <div class="ui section divider"></div>

                                    <div class="ui message">
                                        <div class="header">
                                            JNetPcap Packet Filter Expression
                                        </div>

                                        <ul class="list">
                                            <li>If provided, the following apply:</li>
                                            <ul>
                                                <li>If the /attack flag is set, the following apply:</li>
                                                <ul>
                                                    <li>Matching packets are flagged as attacks.</li>
                                                    <li>Non matching packets are flagged as normal.</li>
                                                </ul>
                                                <li>If the /attack flag is not set, the following apply:</li>
                                                <ul>
                                                    <li>Matching packets are flagged as normal.</li>
                                                    <li>Non matching packets are flagged as attacks.</li>
                                                </ul>
                                            </ul>

                                            <li>If not provided, the following apply:</li>
                                            <ul>
                                                <li>If the /attack flag is set, the following apply:</li>
                                                <ul>
                                                    <li>All packets are flagged as attacks.</li>
                                                </ul>
                                            </ul>
                                            <ul>
                                                <li>If the /attack flag is not set, the following apply:</li>
                                                <ul>
                                                    <li>All packets are flagged as normal.</li>
                                                </ul>
                                            </ul>

                                        </ul>
                                    </div>

                                    <div style="margin-top:20px;display:block;" class="field">
                                        <textarea name="trainingfilter" id="dgtrainingfilter"></textarea>
                                    </div>

                                    <div style="margin-top:20px;display:block;" class="ui toggle checkbox">
                                        <input type="checkbox" name="attackswitch" id="dgattackswitch">
                                        <label>Mark traffic as attack</label>
                                    </div>

                                    <div class="ui section divider"></div>

                                    <div class="ui message">
                                        <div class="header">
                                            JNetPcap Packet Filter Expression
                                        </div>

                                        <ul class="list">
                                            <li>If provided, the following apply:</li>
                                            <ul>
                                                <li>If the /allow flag is set, the following apply:</li>
                                                <ul>
                                                    <li>Matching packets are included in the training set.</li>
                                                    <li>Non matching packets are excluded from the training set.</li>
                                                </ul>
                                                <li>If the /allow flag is not set, the following apply:</li>
                                                <ul>
                                                    <li>Matching packets are excluded from the training set.</li>
                                                    <li>Non matching packets are included in the training set.</li>
                                                </ul>
                                            </ul>

                                            <li>If not provided, the following apply:</li>
                                            <ul>
                                                <li>If the /allow flag is set, the following apply:</li>
                                                <ul>
                                                    <li>All packets are included in the training set.</li>
                                                </ul>
                                            </ul>
                                            <ul>
                                                <li>If the /allow flag is not set, the following apply:</li>
                                                <ul>
                                                    <li>All packets are excluded from the training set.</li>
                                                </ul>
                                            </ul>

                                        </ul>
                                    </div>

                                    <div style="margin-top:20px;display:block;" class="field">
                                        <textarea name="packetfilter" id="dgpacketfilter"></textarea>
                                    </div>

                                    <div style="margin-top:20px;display:block;" class="ui toggle checkbox">
                                        <input type="checkbox" name="packetfilterswitch" id="dgpacketfilterswitch">
                                        <label>Allow</label>
                                    </div>

                                    <div class="ui page dimmer" id="dgstartdimmer">
                                        <div class="content">
                                            <div class="center">
                                                <h2 class="ui inverted icon header">
                                                    <i class="icon circular inverted emphasized green download disk"></i>
                                                    Data Gathering successfully started!
                                                    <div class="sub header">View the progress bar to view its progress</div>
                                                </h2>
                                            </div>
                                        </div>
                                    </div>

                                    <div style="margin-top:20px;">
                                        <a class="ui teal submit button" name="action" id="dgaction" value="start">Start</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="ttab">
                        <h2 class="ui dividing header">Training</h2>
                        <div class="ui grid">
                            <div class="column" id="tcolumn">
                                <h4 class="ui header">About the Training Phase</h4>
                                <p>After capturing the needed information, CHIMERA goes into Training Phase where a decision tree is generated from the data captured during the Data Gathering Phase. The data to be used the normal network baseline is created on a day-to-day basis. The baselines and the simulated attacks act as input datasets to generate the decision tree. The simulated attacks are already trained before the Training Phase of the system.<br>

                                    <br>The ctrain command starts the CHIMERA's model building phase.
                                    <br>The 'normal' model used in the Production Phase is built in this phase.
                                    <br>This phase produces the said model using the training set captured during Data Gathering.
                                    <br>The model produced is stored on a .cmodel file.
                                </p>
                                <div class="ui section divider"></div>

                                <div class="ui success message">
                                    <i class="close icon"></i>
                                    <div class="header">
                                        Training complete!
                                    </div>
                                    <p>Model file can now be downloaded and used in the Production Phase.</p>
                                    <div class="ui active button">
                                        <i class="download icon"></i>
                                        Download
                                    </div>  
                                </div>

                                <h4 class="ui header" id="theaderchange">Training Settings</h4>
                                <div id="thidden"></div>

                                <div class="ui form" id="trainingform" action="Training" method="post">
                                    <div class="ui error message"></div>

                                    <div class="field">
                                        <div class="ui pointing below label">
                                            Contains datasets or the tabled attributes of the traffic gathered during the Data Gathering Phase and should be in the .csv format
                                        </div>
                                        <div class="ui fluid labeled action input">
                                            <div class="ui corner label">
                                                <i class="icon asterisk"></i>
                                            </div>
                                            <input type="text" placeholder="Choose training file..." name="trainingfile" id="ttrainingfile">
                                            <div class="ui button">Browse</div>
                                        </div>
                                    </div>

                                    <div class="field">
                                        <input type="text" placeholder="Output file name..." name="outputfile" id="toutputfile">
                                    </div>

                                    <div class="ui message">
                                        <div class="header">
                                            Attribute filter regular expression
                                        </div>
                                        May be used to exclude certain attributes from the training set.
                                        <ul class="list">
                                            <li>If provided, the following apply:</li>
                                            <ul>
                                                <li>If the /exclude flag is set, the following apply:</li>
                                                <ul>
                                                    <li>Matching attributes are excluded.</li>
                                                    <li>Non matching attributes are included.</li>
                                                </ul>
                                                <li>If the /exclude flag is not set, the following apply</li>
                                                <ul>
                                                    <li>Matching attributes are not included.</li>
                                                    <li>Non matching attrbitues are excluded.</li>
                                                </ul>
                                            </ul>

                                            <li>If not provided, the following apply:</li>
                                            <ul>
                                                <li>If the /exclude flag is set, the following apply:</li>
                                                <ul>
                                                    <li>All attributes are excluded.</li>
                                                </ul>
                                            </ul>
                                            <ul>
                                                <li>If the /exclude flag is not set, the following apply:</li>
                                                <ul>
                                                    <li>All attributes are included.</li>
                                                </ul>
                                            </ul>

                                        </ul>
                                    </div>

                                    <div style="margin-top:20px;display:block;" class="field">
                                        <textarea name="filter" id="tfilter"></textarea>
                                    </div>

                                    <div style="margin-top:20px;display:block;" class="ui toggle checkbox">
                                        <input type="checkbox" name="exclude" id="texclude">
                                        <label>Exlude</label>
                                    </div>

                                    <div class="ui page dimmer" id="tstartdimmer">
                                        <div class="content">
                                            <div class="center">
                                                <h2 class="ui inverted icon header">
                                                    <i class="icon circular inverted emphasized green download disk"></i>
                                                    Training successfully started!
                                                    <div class="sub header">View the progress bar to view its progress</div>
                                                </h2>
                                            </div>
                                        </div>
                                    </div>

                                    <div style="margin-top:20px;">
                                        <a class="ui teal submit button" name="action" value="start">Start</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="ptab">
                        <h2 class="ui dividing header">Production</h2>
                        <div class="ui grid">
                            <div class="column" id="pcolumn">
                                <h4 class="ui header">About the Production Phase</h4>
                                <p>The Production Phase involves analyzing the traffic coming into the DMZ and back, while performing state calculations and updates, and evaluation on inbound traffic using layer 7 inspections and analysis of the current network traffic using the decision tree generated during the Training Phase. The decision tree is responsible for differentiating normal from anomalous behavior. Based on the results, certain packets are either allowed to pass or be dropped.<br>

                                    <br>The cproduce command starts the CHIMERA's Production phase.
                                    <br>The 'normal' model produced in the Training Phase is used in this phase.
                                    <br>This phase checks network traffic for possible Denial-of-Service attacks.
                                    <br>Upon the discovery of an attack, logs and rules can optionally be created.
                                </p>
                                <div class="ui section divider"></div>

                                <div class="ui success message">
                                    <i class="close icon"></i>
                                    <div class="header">
                                        Settings successfully saved!
                                    </div>
                                </div>

                                <h4 class="ui header" id="pheaderchange">Production Settings</h4>
                                <div id="phidden"></div>

                                <div class="ui form" id="productionform" action="Production" method="post">
                                    <div class="ui error message"></div>

                                    <div class="ui pointing below label">
                                        The output file of the training phase in the .cmodel format
                                    </div>

                                    <div class="field">
                                        <div class="ui fluid labeled action input">
                                            <div class="ui corner label">
                                                <i class="icon asterisk"></i>
                                            </div>
                                            <input type="text" placeholder="Choose model file..." name="modelfile" id="pmodelfile">
                                            <div class="ui button">Browse</div>
                                        </div>
                                    </div>

                                    <div class="field" style="margin-top:20px;" >
                                        <input type="text" placeholder="Specify syslog server address..." name="syslog" id="psyslog">
                                    </div>

                                    <div style="margin-top:20px;display:block;" class="ui toggle checkbox">
                                        <input type="checkbox" name="firewall" id="pfirewall">
                                        <label>Create firewall rules</label>
                                    </div>

                                    <div class="ui page dimmer" id="pstartdimmer">
                                        <div class="content">
                                            <div class="center">
                                                <h2 class="ui inverted icon header">
                                                    <i class="icon circular inverted emphasized green download disk"></i>
                                                    Production successfully started!
                                                    <div class="sub header">View the progress bar to view its progress</div>
                                                </h2>
                                            </div>
                                        </div>
                                    </div>

                                    <div style="margin-top:20px;">
                                        <a class="ui teal submit button" name="action" value="start">Start</a>
                                    </div>
                                </div>

                                <div class="ui right overlay very wide floating sidebar" id="pside">
                                    <div class="ui form" style="padding:15px;">
                                        <div class="field">
                                            <p>Output messages</p>
                                            <textarea></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="ctab">
                        <h2 class="ui dividing header">Configurations</h2>
                        <div class="ui grid">
                            <div class="column">
                                <h4 class="ui header">About the Configuration</h4>
                                <p>This page allows the administrator to input additional parameters that will affect the system's operation.<br>

                                    <br>The cdiag command prints the state of a specified component.
                                    <br>The command will only work if there is an ongoing phase."
                                </p>
                                <div class="ui section divider"></div>

                                <h4 class="ui header">Parameters</h4>

                                <form class="ui form" id="configurationform" action="Configuration" method="post">
                                    <div class="two fields">
                                        <div class="field" style="margin-top:20px;" >
                                            <div class="ui pointing below label">
                                                The amount of time before a TCP state is allowed to be idle
                                            </div>
                                            <input type="text" placeholder="TCP state timeout..." name="tcptimeout">
                                        </div>

                                        <div class="field" style="margin-top:20px;" >
                                            <div class="ui pointing below label">
                                                The amount of time before a criteria instance is allowed to be idle
                                            </div>
                                            <input type="text" placeholder="Criteria instance timeout..." name="criteriatimeout">
                                        </div>
                                    </div>

                                    <div class="field" style="margin-top:20px;" >
                                        <div class="ui pointing below label">
                                            The port to listen for control messages during deployment
                                        </div>
                                        <input type="text" placeholder="Control message port number..." name="controlport">
                                    </div>

                                    <div class="ui selection dropdown">
                                        <input name="confinterface" type="hidden">
                                        <label>Protected Interface:</label>
                                        <div class="text">Select</div>
                                        <i class="dropdown icon"></i>
                                        <div class="menu">
                                            <div class="item" data-value="option1">Interface 1</div>
                                            <div class="item" data-value="option2">Interface 2</div>
                                        </div>
                                    </div>

                                    <div class="ui page dimmer" id="cstartdimmer">
                                        <div class="content">
                                            <div class="center">
                                                <h2 class="ui inverted icon header">
                                                    <i class="icon circular inverted emphasized green download disk"></i>
                                                    Configuration successfully saved and applied!
                                                    <div class="sub header">Configuration has been save and applied</div>
                                                </h2>
                                            </div>
                                        </div>
                                    </div>

                                    <div style="margin-top:20px;">
                                        <a class="ui teal submit button" name="action" value="start">Start</a>
                                        <a class="ui red submit button" name="action" value="stop">Stop</a>
                                        <div class="ui active button" id="cshowoutputmessages">
                                            <i class="browser icon"></i>
                                            Show Output Messages
                                        </div>
                                    </div>

                                    <div class="ui right overlay very wide floating sidebar" id="cside">
                                        <div class="ui form" style="padding:15px;">
                                            <div class="field">
                                                <p>Configuration content</p>
                                                <textarea></textarea>
                                            </div>
                                        </div>
                                    </div>

                                </form>

                            </div>
                        </div>
                    </div>

                    <div id="itab">
                        <h2 class="ui dividing header">Interfaces</h2>
                        <div class="ui grid">
                            <div class="column">
                                <h4 class="ui header">About the Interfaces</h4>
                                <p>
                                    The cifaces command prints the configuration of all detected interfaces.
                                    <br>The index of an interface is indicated by its position in the output list.
                                </p>
                                <div class="ui section divider"></div>

                                <div class="ui list">
                                    <div class="item">
                                        <i class="sitemap image icon"></i>
                                        <div class="content">
                                            <div class="header">Interface 1</div>
                                            Description of the first interface
                                        </div>
                                        <div class="list">
                                            <div class="item">
                                                <i class="desktop icon"></i>
                                                <div class="content">
                                                    <a class="header">Hardware Address</a>
                                                    <div class="description">01-23-45-67-89-AB-CD-EF</div>
                                                </div>
                                            </div>
                                            <div class="item">
                                                <i class="fork code icon"></i>
                                                <div class="content">
                                                    <a class="header">Interface Address</a>
                                                    <div class="description">Is this your address? I'm getting dropped off from the SPCA...</div>
                                                </div>
                                            </div>
                                            <div class="item">
                                                <i class="url icon"></i>
                                                <div class="content">
                                                    <a class="header">IP Address</a>
                                                    <div class="description">192.168.6.2</div>
                                                </div>
                                            </div>
                                            <div class="item">
                                                <i class="code icon"></i>
                                                <div class="content">
                                                    <a class="header">Subnet Mask</a>
                                                    <div class="description">255.255.255.0</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <i class="sitemap image icon"></i>
                                        <div class="content">
                                            <div class="header">Interface 2</div>
                                            Description of the second interface
                                        </div>
                                        <div class="list">
                                            <div class="item">
                                                <i class="desktop icon"></i>
                                                <div class="content">
                                                    <a class="header">Hardware Address</a>
                                                    <div class="description">01-23-45-67-89-AB-CD-EF</div>
                                                </div>
                                            </div>
                                            <div class="item">
                                                <i class="fork code icon"></i>
                                                <div class="content">
                                                    <a class="header">Interface Address</a>
                                                    <div class="description">Is this your address? I'm getting dropped off from the SPCA...</div>
                                                </div>
                                            </div>
                                            <div class="item">
                                                <i class="url icon"></i>
                                                <div class="content">
                                                    <a class="header">IP Address</a>
                                                    <div class="description">192.168.7.2</div>
                                                </div>
                                            </div>
                                            <div class="item">
                                                <i class="code icon"></i>
                                                <div class="content">
                                                    <a class="header">Subnet Mask</a>
                                                    <div class="description">255.255.255.0</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>

                    <div id="dtab">
                        <h2 class="ui dividing header">Diagnosis</h2>
                        <div class="ui grid">
                            <div class="column">
                                <h4 class="ui header">About the Diagnosis</h4>
                                <p>
                                    The cdiag command prints the state of a specified component.
                                    <br>The command will only work if there is an ongoing phase.
                                </p>
                                <div class="ui section divider"></div>

                                <div class="ui selection dropdown">
                                    <label>Component:</label>
                                    <div class="text">Select</div>
                                    <i class="dropdown icon"></i>
                                    <div class="menu">
                                        <div class="item" data-value="option1">Component 1</div>
                                        <div class="item" data-value="option2">Component 2</div>
                                    </div>
                                </div>

                                <div style="margin-top:20px;">
                                    <div class="ui active button" id="dshowoutputmessages">
                                        <i class="browser icon"></i>
                                        Diagnose
                                    </div>
                                </div>

                                <div class="ui right overlay very wide floating sidebar" id="dside">
                                    <div class="ui form" style="padding:15px;">
                                        <div class="field">
                                            <p>Component state</p>
                                            <textarea></textarea>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div id="utab">
                        <h2 class="ui dividing header">Users</h2>
                        <div class="ui grid">
                            <div class="column">
                                <h4 class="ui header">About Users</h4>
                                <p>These are sets of username and passwords assigned to people who are authorized to configure the system with certain levels of permissions and priveleges.
                                </p>
                                <div class="ui section divider"></div>

                                <div class="ui success message">
                                    <i class="close icon"></i>
                                    <div class="header">
                                        User successfully added!
                                    </div>
                                </div>

                                <div class="ui fluid left icon action input">
                                    <i class="users icon"></i>
                                    <input type="text" placeholder="Search users...">
                                    <div class="ui button">Search</div>
                                </div>

                                <div class="ui page dimmer" id="ustartdimmer">
                                    <div class="content">
                                        <div class="center">
                                            <h2 class="ui inverted icon header">
                                                <i class="icon circular inverted emphasized green download disk"></i>
                                                User successfully added!
                                                <div class="sub header">User can now login to the system</div>
                                            </h2>
                                        </div>
                                    </div>
                                </div>

                                <div style="margin-top:20px;" class="ui active button" id="newuser">
                                    <i class="user icon"></i>
                                    Add User
                                </div>

                                <div class="ui right overlay very wide floating sidebar" id="uside">
                                    <form class="ui form" id="adduserform" action="AddUser" method="post" style="margin:20px;">

                                        <div class="ui error message"></div>
                                        <div class="two fields">
                                            <div class="field">
                                                <div class="ui labeled icon input">
                                                    <div class="ui corner label">
                                                        <i class="icon asterisk"></i>
                                                    </div>
                                                    <input placeholder="First Name" name="first-name" type="text">
                                                </div>
                                            </div>
                                            <div class="field">
                                                <div class="ui labeled icon input">
                                                    <div class="ui corner label">
                                                        <i class="icon asterisk"></i>
                                                    </div>
                                                    <input placeholder="Last Name" name="last-name" type="text">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="field">
                                            <div class="ui left labeled icon input">
                                                <input type="text" placeholder="Username" name="username">
                                                <i class="user icon"></i>
                                                <div class="ui corner label">
                                                    <i class="icon asterisk"></i>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="field">
                                            <div class="ui left labeled icon input">
                                                <input type="password" name="password">
                                                <i class="lock icon"></i>
                                                <div class="ui corner label">
                                                    <i class="icon asterisk"></i>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="field">
                                            <div class="ui selection dropdown">
                                                <input type="hidden" name="role">
                                                <label>Role:</label>
                                                <div class="text">Select</div>
                                                <i class="dropdown icon"></i>
                                                <div class="menu">
                                                    <div class="item" data-value="admin">Admin</div>
                                                    <div class="item" data-value="moderator">Moderator</div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="inline field">
                                            <div class="ui checkbox">
                                                <input type="checkbox" name="terms">
                                                <label>I agree to the Terms and Conditions</label>
                                            </div>
                                        </div>

                                        <div class="ui blue submit button" name="action" value="submit">Submit</div>

                                    </form>
                                </div>

                                <div class="ui divided selection list">
                                    <div class="item">
                                        <img class="ui avatar image" src="images/users/emer.jpg">
                                        <div class="content">
                                            <div class="header">Emerson Chua</div>
                                            Admin
                                        </div>
                                    </div>
                                    <div class="item">
                                        <img class="ui avatar image" src="images/users/nikkol.jpg">
                                        <div class="content">
                                            <div class="header">Nikkol Morales</div>
                                            Admin
                                        </div>
                                    </div>
                                    <div class="item">
                                        <img class="ui avatar image" src="images/users/johnp.jpg">
                                        <div class="content">
                                            <div class="header">John Penafiel</div>
                                            Admin
                                        </div>
                                    </div>
                                    <div class="item">
                                        <img class="ui avatar image" src="images/users/jeno.jpg">
                                        <div class="content">
                                            <div class="header">Jeno Rigor</div>
                                            Admin
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div> <!-- Content Area End -->

            </div>
        </div>
    </body>
</html>
