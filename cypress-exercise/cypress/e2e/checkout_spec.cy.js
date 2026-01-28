describe('Checkout Test', () => {
    it('Should complete checkout process', () => {
        cy.visit('https://www.saucedemo.com');
        cy.get('#user-name').type('standard_user');
        cy.get('#password').type('secret_sauce');
        cy.get('#login-button').click();

        // Add to cart
        cy.get('.inventory_item').first().find('.btn_inventory').click();

        // Go to cart
        cy.get('.shopping_cart_link').click();

        // Checkout
        cy.get('#checkout').click();

        // Fill info
        cy.get('#first-name').type('John');
        cy.get('#last-name').type('Doe');
        cy.get('#postal-code').type('12345');

        // Continue
        cy.get('#continue').click();

        // Verify
        cy.url().should('include', '/checkout-step-two.html');
    });
});
