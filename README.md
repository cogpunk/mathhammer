# Cogpunk Mathhammer

Cogpunk Mathhammer is a framework for determining the probabilities and statistics of the game of Warhammer 40,000 (8th & 9th Editions)

[![Build Status](https://travis-ci.com/cogpunk/mathhammer.svg?branch=main)](https://travis-ci.com/cogpunk/mathhammer)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=cogpunk_mathhammer&metric=alert_status)](https://sonarcloud.io/dashboard?id=cogpunk_mathhammer)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=cogpunk_mathhammer&metric=coverage)](https://sonarcloud.io/dashboard?id=cogpunk_mathhammer)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=cogpunk_mathhammer&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=cogpunk_mathhammer)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=cogpunk_mathhammer&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=cogpunk_mathhammer)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=cogpunk_mathhammer&metric=security_rating)](https://sonarcloud.io/dashboard?id=cogpunk_mathhammer)
[![Apache License, Version 2.0](https://img.shields.io/github/license/cogpunk/mathhammer)](https://opensource.org/licenses/Apache-2.0)
[![Cogpunk Mathhammer](https://maven-badges.herokuapp.com/maven-central/com.cogpunk/cogpunk-mathhammer/badge.svg)](https://search.maven.org/artifact/com.cogpunk/cogpunk-mathhammer/)

## Building from source

The build requires a Java 6 JDK (or higher) and uses [Maven](https://maven.apache.org)

	mvn install

## Adding as a dependency

	<dependency>
		<groupId>com.cogpunk</groupId>
		<artifactId>cogpunk-mathhammer</artifactId>
		<version>1.0.0</version>
	</dependency>

## License

Code is under the [Apache Licence v2](https://www.apache.org/licenses/LICENSE-2.0.txt).

## Example

	Statistics statistics = new Statistics(
				new AverageRollCalculator(
						new FractionOperator()), 
				new AttackDamageProfileCalculator());
	
	CombatantProfile intercessor = new CombatantProfileImpl(
		new FixedValue(1), // Number of attacks probability Profile
		3,                 // To Hit Target
		0,                 // To Hit Modifier
		ReRoll.NONE,       // To Hit Re-roll
		4,                 // Strength
		4,                 // Toughness
		0,                 // To Wound Modifier
		ReRoll.NONE,       // To Wound Re roll
		3,                 // Save Target
		null,              // Invulnerable Save target
		-1,                // Armour Penetration Modifier
		ReRoll.NONE,       // Save Re roll
		new FixedValue(1), // Damage probability Profile
		2,                 // Wounds
		18                 // Points Cost
	);
	
	CombatantProfile necronWarrior =  new CombatantProfileImpl(
		new FixedValue(1), // Number of attacks probability Profile
		3,                 // To Hit Target
		0,                 // To Hit Modifier
		ReRoll.NONE,       // To Hit Re-roll
		4,                 // Strength
		4,                 // Toughness
		0,                 // To Wound Modifier
		ReRoll.NONE,       // To Wound Re roll
		4,                 // Save Target
		null,              // Invulnerable Save target
		-1,                // Armour Penetration Modifier
		ReRoll.NONE,       // Save Re roll
		new FixedValue(1), // Damage probability Profile
		1,                 // Wounds
		12                 // Points Cost
	);
	
	// Determine the kill ratio of the Intercessor vs the Necron Warrior
	
	System.out.println(statistics.killRatio(intercessor, necronWarrior));

Which returns the result of 8/3, meaning that 3 Intercessors will be killed for every 8 Necron Warriors

## Disclaimer

This code is completely unofficial and in no way endorsed by Games Workshop Limited.

Necrons, Intercessors and the Warhammer 40,000 rules are either ®, ™ and/or © Games Workshop Ltd, variably registered in the UK and other countries around the world. Used without permission. No challenge to their status intended. All Rights Reserved to their respective owners.

No challenge whatsoever is intended to the status of any intellectual property rights of Games Workshop Limited, including, but not limited to trademarks and copyrights of Games Workshop Limited. This has no legal connection or relationship to Games Workshop Limited.