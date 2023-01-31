function randomID() {
    var str = 'qwertyuiopasdfghjklzxcvbnm1234567890';
    var vID = '';
    for (var i=0; i < 5; i++) {
        var rIndex =Math.floor(Math.random() * str.length);
        vID = vID + str.substring(rIndex, rIndex + 1);
    }
    return vID;
}