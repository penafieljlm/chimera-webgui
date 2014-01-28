<%-- 
    Document   : login
    Created on : 01 14, 14, 10:12:41 PM
    Author     : Emerson Chua
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700|Open+Sans:300italic,400,300,700' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="packaged/css/semantic.css">
                
        <script src="jquery.js"></script>
        <script src="packaged/javascript/semantic.js"></script>
        
        <script>
            $(document).ready(function() {
                var rules = {
                    username: {
                      identifier  : 'username',
                      rules: [
                        {
                          type   : 'empty',
                          prompt : 'Please enter your username'
                        }
                      ]
                    },
                    password: {
                      identifier  : 'password',
                      rules: [
                        {
                          type   : 'empty',
                          prompt : 'Please enter your password'
                        }
                      ]
                    }
                };
                
                $('#loginform').form(rules);
            });
        </script>
        
        <style>
            body{
                font-family: "Open Sans", "Helvetica Neue", "Helvetica", "Arial", sans-serif;
                background-image:url('images/desk.png');
                background-repeat:no-repeat;
                background-position:center;
                background-size:cover;
            }
            .tint{
                background-color:white;
                opacity:.70;
                position:fixed;
                top:0px;
                left:0px;
                width:100%;
                height:100%;
            }
            .mainbody{
                width:600px;
                margin-top:150px;
                margin-bottom:150px;
                margin-left:auto;
                margin-right:auto;
            }
        </style>
    <body>
        <div class="tint"></div>
        <div class="mainbody">
            <div class="ui page grid">
                <div class="row">
                <center><img class="ui medium image" src="images/chimera2.png"></center>
                </div>
                
                <div class="row">
                <div class="column" style="width:100%;">
                    <div class="ui form raised segment" id="loginform">
                        <div class="ui error message">
                            <div class="header">We noticed some issues</div>
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
                        
                        <div class="fluid ui blue submit button">Login</div>      
                    </div>
                </div>
                </div>
                <!--
                <div class="row">
                    <div class="ui small images">
                        <img class="ui image" src="images/weka.png">
                        <img class="ui image" src="images/weka.png">
                        <img class="ui image" src="images/weka.png">
                        <img class="ui image" src="images/weka.png">
                    </div>
                </div>
                -->
            </div>
        </div>
    </body>
</html>