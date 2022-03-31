INSERT INTO RECEPT (ID,NAME,PRODUCE,CONDIMENTS,BAKING_AND_SPICES,LIQUIDS,WATER,SUGAR)
VALUES (1,'Tom Collins Cocktail','1 cherry & 1 Lemon round or 1/2 orange round','1oz Lemon juice & 1/2oz syrup','1/4 cup Sugar','1/4 cup water & 2oz Gin',true,true);
INSERT INTO RECEPT (ID,NAME,PRODUCE,CONDIMENTS,BAKING_AND_SPICES,LIQUIDS,WATER,SUGAR)
VALUES (2,'Grapefruit Gin Cocktail','Grapefruit Slice','1 oz Simple Syrup , 3 oz Grapefruit Juice','Rosemary Sprig','2oz water  & 2oz Gin',true,false);
INSERT INTO RECEPT (ID,NAME,PRODUCE,CONDIMENTS,BAKING_AND_SPICES,LIQUIDS,WATER,SUGAR)
VALUES (3,'Classic French 75 Cocktail','1 lemon peel','3/4oz Lemon juice , 3/4 oz Simple syrup','1/4 cup Honey or sugar','2 oz Champagne & 1/2oz Gin',false,false);
INSERT INTO RECEPT (ID,NAME,PRODUCE,CONDIMENTS,BAKING_AND_SPICES,LIQUIDS,WATER,SUGAR)
VALUES (4,'Matcha pina colada','Bar spoon matcha tea , 1 Lemon round or 1/2 orange round','1oz fresh pineapple juice , 1/3oz fresh lemon juice & 2oz coconut milk','1/4 cup Sugar','2oz Gin & 4/3oz water',true,true);

INSERT INTO BRAND (ID,NAME,INTRODUCED,DESCRIPTION,recept_id)
VALUES (nextval('BRAND_SEQ'),'Beefeater: Beste goedkope gin',1671,'De delicate smaakcombinatie maakt het mogelijk om puur van Beefeater gin te genieten, al raad ik aan hem te mengen in een cocktail. Hij bevat een geurige mix van onder meer engelwortel en -zaad, jeneverbes, korianderzaad, amandelen, zoethout, Sevilla-sinaasappelen, citroenschil en orriswortel.',4);
INSERT INTO BRAND (ID,NAME,INTRODUCED,DESCRIPTION,recept_id)
VALUES (nextval('BRAND_SEQ'),'Opihr: Best smakende gin',1772,'Het is een drankje met een verschil dat het beste gedronken kan worden met ijs of puur om echt te proeven van elke gebruikte botanische stof. Opihr wordt gemaakt met Indonesische kubusbessen, Indiase zwarte peper en Marokkaanse koriander, die allemaal samen een unieke, kruidige drank vormen.',3);
INSERT INTO BRAND (ID,NAME,INTRODUCED,DESCRIPTION,recept_id)
VALUES (nextval('BRAND_SEQ'),'Tanqueray: Beste London Dry Gin',1830,'In tegenstelling tot vele andere gin merken, houdt Tanqueray zijn botanisch recept geheim. Echter, het is bekend dat het bestaat uit slechts vier verschillende componenten: jeneverbes, koriander, engelwortel en zoethout.',1);
INSERT INTO BRAND (ID,NAME,INTRODUCED,DESCRIPTION,recept_id)
VALUES (nextval('BRAND_SEQ'),'Malfy: Best gekruide gin',2016,'Malfy biedt een verscheidenheid aan smaken, maar de duidelijke favoriet is de Gin Con Limone. Het merk gebruikt aan de kust geteelde Amalfi-citroenen en Siciliaanse variëteiten in zijn gin met citroensmaak, en belooft bij elke slok de smaak van de zon.',2);

