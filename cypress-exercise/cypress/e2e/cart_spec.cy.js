describe('Cart Test', () => {
    beforeEach(() => {
        cy.visit('https://www.saucedemo.com');
        cy.get('#user-name').type('standard_user');
        cy.get('#password').type('secret_sauce');
        cy.get('#login-button').click();
    });

    it('Should add a product to the cart', () => {
        cy.get('.inventory_item').first().find('.btn_inventory').click();
        cy.get('.shopping_cart_badge').should('have.text', '1');
    });

    it('Should sort products by price low to high', () => {
        cy.get('.product_sort_container').select('lohi');
        cy.get('.inventory_item_price').first().should('have.text', '$7.99');
    });

    it('Should remove a product from the cart', () => {
        // Add product first
        cy.get('.inventory_item').first().find('.btn_inventory').click();
        cy.get('.shopping_cart_badge').should('have.text', '1');

        // Click Remove (the button text changes, but class remains btn_inventory)
        cy.get('.inventory_item').first().find('.btn_inventory').click();

        // Verify cart badge is gone or 0. usually it disappears in saucedemo
        cy.get('.shopping_cart_badge').should('not.exist');
    });
});
