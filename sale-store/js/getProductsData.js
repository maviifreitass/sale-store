
var simpleStore = {
    products: [
        { id: 1, name: "Produto 1", price: "$10.00", image: "path/to/image1.jpg", description: "Descrição do Produto 1" },
        { id: 2, name: "Produto 2", price: "$20.00", image: "path/to/image2.jpg", description: "Descrição do Produto 2" }
    ],
    plugins: {},
    renderSingleProduct: function (id, s) {

        s.container.fadeOut(s.fadeSpeed, function () {

            var tmpl = $('#product-detail-template').html(),
                $tmpl = $(tmpl);

            products.forEach(function (product) {
                if (product.id == id) {

                    // Insert data into template
                    simpleStore.insertData($tmpl, product);

                    // Load detail view into main container
                    s.container.html($tmpl);

                    s.container.fadeIn(s.fadeSpeed);
                }
            });
        });
    },

    insertData: function (tmpl, product) {
        tmpl.find('.item_thumb').attr("src", product.image);
        tmpl.find('.item_name').text(product.name);
        tmpl.find('.item_price').text(product.price);
        tmpl.find('.item_description').text(product.description);
    }

}