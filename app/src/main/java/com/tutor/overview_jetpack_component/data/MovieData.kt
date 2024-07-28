package com.tutor.overview_jetpack_component.data

data class Movie(
	val id: Int,
	val title: String,
	val year: Int,
	val plot: String
)

val movieLists = listOf(
	Movie(
		id = 1,
		title = "Avatar",
		year = 2009,
		plot = "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home."
	),
	Movie(
		id = 2,
		title = "I Am Legend",
		year = 2007,
		plot = "Years after a plague kills most of humanity and transforms the rest into monsters, the sole survivor in New York City struggles valiantly to find a cure."
	),
	Movie(
		id = 3,
		title = "300",
		year = 2006,
		plot = "King Leonidas of Sparta and a force of 300 men fight the Persians at Thermopylae in 480 B.C."
	),
	Movie(
		id = 4,
		title = "The Avengers",
		year = 2012,
		plot = "Earth's mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity."
	),
	Movie(
		id = 5,
		title = "The Wolf of Wall Street",
		year = 2013,
		plot = "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government."
	),
	Movie(
		id = 6,
		title = "Interstellar",
		year = 2014,
		plot = "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival."
	),
	Movie(
		id = 7,
		title = "Game of Thrones",
		year = 2011,
		plot = "While a civil war brews between several noble families in Westeros, the children of the former rulers of the land attempt to rise up to power. Meanwhile a forgotten race, bent on destruction, plans to return after thousands of years in the North."
	),
	Movie(
		id = 8,
		title = "Vikings",
		year = 2013,
		plot = "The world of the Vikings is brought to life through the journey of Ragnar Lothbrok, the first Viking to emerge from Norse legend and onto the pages of history - a man on the edge of myth."
	),
	Movie(
		id = 9,
		title = "Gotham",
		year = 2014,
		plot = "The story behind Detective James Gordon's rise to prominence in Gotham City in the years before Batman's arrival."
	),
	Movie(
		id = 10,
		title = "Power",
		year = 2014,
		plot = "James \"Ghost\" St. Patrick, a wealthy New York night club owner who has it all, catering for the city's elite and dreaming big, lives a double life as a drug kingpin."
	),
	Movie(
		id = 11,
		title = "Narcos",
		year = 2015,
		plot = "A chronicled look at the criminal exploits of Colombian drug lord Pablo Escobar."
	),
	Movie(
		id = 12,
		title = "Breaking Bad",
		year = 2008,
		plot = "A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family's financial future."
	),
	Movie(
		id = 13,
		title = "Doctor Strange",
		year = 2016,
		plot = "After his career is destroyed, a brilliant but arrogant and conceited surgeon gets a new lease on life when a sorcerer takes him under her wing and trains him to defend the world against evil."
	),
	Movie(
		id = 14,
		title = "Rogue One: A Star Wars Story",
		year = 2016,
		plot = "The Rebellion makes a risky move to steal the plans to the Death Star, setting up the epic saga to follow."
	),
	Movie(
		id = 15,
		title = "Assassin's Creed",
		year = 2016,
		plot = "When Callum Lynch explores the memories of his ancestor Aguilar and gains the skills of a Master Assassin, he discovers he is a descendant of the secret Assassins society."
	),
	Movie(
		id = 16,
		title = "Luke Cage",
		year = 2016,
		plot = "Given superstrength and durability by a sabotaged experiment, a wrongly accused man escapes prison to become a superhero for hire."
	)
)