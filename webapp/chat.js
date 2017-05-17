var messages = "";
var isAutorise = false;
var uuid = "";
var login = "";

function sendMessage() {
    $.post(
        "/write",
        {login: login, uuid: uuid, message: $(".chat_new_messages").val()},
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
        alert("I'm ready")
        if (isAutorise){
            setInterval(getMassages(), 5000);
        }
});

function getMassages() {
    $.post(
        "/serv",
        {login: login, uuid: uuid},
        function(callback){
            messages = "";
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
    alert("pic");
    $.post(
        "/login",
        {login: $(".login").val(), pass: $(".pass").val()},
        function(callback){
            alert("pok pok pok");
            uuid = callback;
            $(".autorisation").hide();
            $(".autorise_chat").show();
            isAutorise = true;
            getMassages();
        }
    );
}