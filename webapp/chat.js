var messages = "";

function sendMessage() {
    $.post(
        "/write",
        {message: $(".chat_new_messages").val()},
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
        getMassages();
        setInterval(getMassages(), 5000);
});

function getMassages() {
    $.post(
        "/serv",
        function(callback){
            var jsonSource = $.parseJSON(callback);
            var massageHistory = jsonSource.messages;
            massageHistory.forEach(function (massage) {
                messages += massage;
            });
            $(".chat_messages_list").val(messages);
        }
    );
}