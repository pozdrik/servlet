var messages = "";
var isAuthorise = false;
var uuid = "";
var login = "";
var currentDate = new Date(0);

function sendMessage() {
    $.post(
        "/write",
        {login: login, uuid: uuid, message: $(".chat_new_messages").val(), date: new Date()},
        function (callback) {
            getMassages();
        }
    );
}

$(document).ready(function () {
        $(".chat_submit_massage").click(function(){
            sendMessage();
            $(".chat_new_messages").val('');
        });
        $(".login_password_send").click(function(){
                sendAutorise();
        });
        if (isAuthorise){
                setInterval(getMassages(), 5000);
        }

});

function getMassages() {
    $.post(
        "/serv",
        {login: login, uuid: uuid, date: currentDate},
        function(callback){
            currentDate = new Date();
            var jsonSource = $.parseJSON(callback);
            var massageHistory = jsonSource.messages;
            massageHistory.forEach(function (massage) {
                messages += massage;
            });
            $(".chat_messages_list").val(messages);
        }
    );
}

function sendAutorise() {
    login = $(".login").val();
    $.post(
        "/login",
        {login: $(".login").val(), pass: $(".pass").val()},
        function(callback){
            uuid = callback;
            $(".autorisation").hide();
            $(".autorise_chat").show();
            isAuthorise = true;
            getMassages();
        }
    );
}