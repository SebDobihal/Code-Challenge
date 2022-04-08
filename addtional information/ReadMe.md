CodeChallenge was implemented with Java 18, OpenJDK18. 

additionally, I'm using these libs: 
- JUnit5
- joda-money
- apache commons-csv 

versions are specified in the POM.

Regarding my Solution:

1. Verkauf eines bestimmten Buches von einem Shop an einen Kunden:

   Is handled inside the Shop, with the sellBook method. The book will only
   be sold if:
   - the shop has it in its inventory 
   - and the costumer has enough money to pay for the book 
    
   The shop has no Information about how much money the costumer has, 
   it only gets the Information if the costumer has enough money or not.


2. Filtern von Büchern: 

   is handled inside the Shop class as well, by the
   filterBooksByGenre method. I put the method there because of the encapsulation
   of information of the shop. A costumer is not needed for this
   method.
    

3. Entfernen von Duplikaten:

   is not needed in my Solution, because a shop uses a Map instead of a List 
   to handle their inventory. Which has plenty of perks:
   - no duplicates
   - easier tracking of the quantity for each book.
   - easier implementation for new features, like costumers ordering
     books, or adding a higher quantity of one book instead of a single one.
     Every shop should be able to provide any book to a costumer, even if it has
     to order it.
   - especially if a method need to handle higher quantities of books.
   - could even provide better Performance, if concurrency is taken
     into account


4. Vergleich:

   is handled inside the ShopUtils class, by the compareShops method.
   I decided against placing this method in the costumer class as
   the exercise implies, because even if the wish for this
   method is coming from the costumer, the method has applications outside the need
   for a shop or customer object to call it. => Utils


5. Bücher hinzufügen:

   is handled inside the shop class, by the method addBook. Which uses
   the isValidIsbn method of the IsbnUtils class. I separated the
   validation because it has many more applications, which are not connected to a Shop.
   The validation might be even more useful when used at the instantiation
   of books, but the requirements were not specific enough, and I don't know 
   if books with malformed ISBN couldn't / shouldn't exist. 
   But this means the ISBN isn't enough, in this context, to identify a book by itself.
   So I needed to check more than attribute in the equals-method of book.
