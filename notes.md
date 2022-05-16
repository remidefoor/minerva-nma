- todo

  - max width book title books page
  - padding books recycler view (item decoration)
  
  # questions
  
  - Waar roep ik de functie op om te controleren of de gebruiker is ingelogd en indien nodig de main activity te openen? -> in je LoginFragment
  
  - Hoe return ik in een coroutine? Ik moet namelijk kunnen laten weten aan mijn fragment of de authenticatie geslaagd is om vervolgens al dan niet de main activity te openen.
    -> hiervoor gebruik je de functionaliteit van live data. Je maakt een variabele bv LoginState die de waarde SUCCES of FAIL kan hebben. In je coroutine geef je deze de juiste waarde. Dmv het obsver-pattern zal je fragment dan getriggerd worden. 
    Ik heb een voorbeeld in je code toegevoegd. (beter is wel om niet met een string te testen)
  
  - Er wordt geen nieuwe notes API call gedaan wanneer ik naar de noties van een ander boek navigeer, waardoor voor elk boek de notities hetzelfde zijn. -> dit kan verholpen worden door bij het sluiten van je notities fragment (als je dus terugkeert naar het overzicht van je boeken), 
  
  - Na authenticatie is de gebruiker nog niet beschikbaar in de Room database om in de main activity opgehaald te worden voor API calls.-> je moet bij het inloggen de user opslaan in de db
  
  - De bottom navigation navigeert niet naar het books fragment, maar terug naar het laatst geopende fragment. 