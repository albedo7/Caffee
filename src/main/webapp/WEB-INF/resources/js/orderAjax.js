function getXMLHttpRequest() {
    var xmlHttpReq = false;
    if (window.XMLHttpRequest) {
        xmlHttpReq = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        try {
            xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (exp1) {
            try {
                xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (exp2) {
                xmlHttpReq = false;
            }
        }
    }
    return xmlHttpReq;
}
function addMealToOrder(id) {
    var xmlHttpRequest = getXMLHttpRequest();
    xmlHttpRequest.onreadystatechange = getReadyStateHandler(xmlHttpRequest);
    xmlHttpRequest.open("POST", "book/add?id=" + id, true);
    xmlHttpRequest.setRequestHeader("Content-Type",
        "application/x-www-form-urlencoded");
    xmlHttpRequest.send(null);
}

function getReadyStateHandler(xmlHttpRequest) {

    return function() {
        if (xmlHttpRequest.readyState == 4) {
            if (xmlHttpRequest.status == 200) {
                document.getElementById("order").innerHTML = xmlHttpRequest.responseText;
            } else {
                alert("HTTP error " + xmlHttpRequest.status + ": " + xmlHttpRequest.statusText);
            }
        }
    };
}