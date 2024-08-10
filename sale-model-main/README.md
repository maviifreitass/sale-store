# Requests Back-End

GET para lista de produtos:
http://localhost:8080/sale/api/product

Retorno:
[
    {
        "id": 1,
        "name": "Camiseta",
        "mark": "Marca A",
        "category": "Roupas",
        "price": 49.9,
        "stock": 100
    },
    {
        "id": 2,
        "name": "Tenis",
        "mark": "Marca B",
        "category": "Calçados",
        "price": 149.9,
        "stock": 50
    }
]

POST para criar produtos
http://localhost:8080/sale/api/order/create

Payload:
{
    "clientId": 1,
    "productId": 4,
    "productSize": "M",
    "productQuantity": 3
}

Retorno: 
201 Created + objeto criado.