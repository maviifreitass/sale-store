function fazGet(url) {
    console.log("Requesting URL:", url);
    let request = new XMLHttpRequest();
    request.open("GET", url, true);

    request.send();

    request.onload = function() {
        if (this.status >= 200 && this.status < 300) {
            console.log('Response:', this.responseText);
        } else {
            console.error('Request failed with status:', this.status);
        }
    };

    return request.responseText;
}

function cadastraUsuario() {
    event.preventDefault();
    let url = "http://localhost:8080/sale/api/product";

    fazGet(url);
}