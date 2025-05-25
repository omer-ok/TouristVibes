package com.fooddelivery.eatexpress.models

import java.time.Instant
import java.util.Date

//@Entity(tableName = "food_tbl")
data class TourData(
  //  @PrimaryKey
    //val id:UUID =UUID.randomUUID(),
    val id:Int,
    val image:String,
    val name :String,
    val rating:Float,
    val description:String,
    val updateDate : Date = Date.from(Instant.now())
)

fun getTourData():List<TourData>{
    return listOf(
        TourData(1,"TowerLondon","Tower of London",4f,"The Tower of London is one of the world’s most famous fortresses and has seen service as a royal palace, prison, armory and even a zoo. Fortress, prison, royal mint, and now a tourist attraction. The Tower of London has seen many lives over the past centuries and persists to this day as a powerful symbol of British heritage",),
        TourData(2,"LondonEye","The London Eye",5f,"Are you looking to take your London experience to new heights? Take a ride on Europe’s tallest cantilevered observation wheel in the heart of the city – a must-do on your London vacation."),
        TourData(3,"UberBoat","Uber Boat by Thames Clippers 1-day River Roamer",4f,"How better to see the best of London’s landmarks than from the water!\n" +
                "\n" + "If you are planning to visit London and looking for a unique way to explore the city, why not take a ride on an Uber Boat? Uber Boat, formerly known as Thames Clippers, is a river bus service that operates on the River Thames in London. With 23 piers across London, the service is a convenient and scenic way to travel around the city."),
        TourData(4,"Shard","The View from The Shard",3f,"From a 1970s office block to one of the world’s most distinguished buildings, ascend to London’s highest viewpoint at The View from The Shard.\n" +
                "\n" + "Journey to a height of up to 800ft (244m); to the top of London’s tallest observation platform and premium visitor attraction, The View from The Shard. Formerly known as the London Bridge Tower"),
        TourData(5,"Westminster","Westminster Abbey",3f,"Just a short walk from the Thames, Westminster Abbey is a must-see and a significant structure in British history. This beautiful gothic church is a UNESCO World Heritage Site popular with many visitors to London. Complete with paintings, stained glass windows, and other religious artifacts"),
        TourData(6,"PaulCathedral","St Paul’s Cathedral",5f,"From royal weddings and state funerals to famous burials and more, St Paul’s Cathedral has played a major role in London’s history.\n" +
                "\n" + "Situated near the River Thames, St Paul’s Cathedral is one of the capital’s most iconic buildings. "),
        TourData(7,"TowerBridge","Tower Bridge",5f,"Operating as a working bridge for over 125 years, Tower Bridge offers numerous exhibitions detailing its history, a range of family-friendly experiences as well as a unique viewing experience of the River Thames from its iconic Glass Floors in the 42-meter high walkways"),
        TourData(8,"WindsorCastle","Windsor Castle",5f,"For over 1,000 years, Windsor Castle has been the English royal residence. This stunning castle, occupying 10.5 hectares/26.5 acres of land, is the largest and oldest occupied castle in the world.\n" +
                "\n" + "William the Conqueror first made use of this vast space after the Battle of Hastings in 1066, crafting the castle out of wood in what was known as a motte-and-bailey style."),
        TourData(9,"GreatFireofLondon","The Monument to the Great Fire of London",5f,"Designed by Sir Christopher Wren (architect of the 51 city structures, including the iconic St. Paul's Cathedral), the monument was built to commemorate the Great Fire of London. It offers one of the best views of London while learning about an important moment in London's history. "),
        TourData(10,"MocoMuseum","Moco Museum London",3f,"Moco Museum London is a vibrant contemporary art gallery boasting works from world-famous artists including Banksy, Tracey Emin, KAWS, and more. Step inside the striking building and explore an awe-inspiring collection of modern artworks spotlighting both celebrated artists and emerging talent.")
    )
}

fun getTourDetails(tourId:Int):TourData{
    val tourData:TourData
    when(tourId){
        1 ->{
            tourData = TourData(1,"TowerLondon","Tower of London",4f,"The Tower of London is one of the world’s most famous fortresses and has seen service as a royal palace, prison, armory and even a zoo. Fortress, prison, royal mint, and now a tourist attraction. The Tower of London has seen many lives over the past centuries and persists to this day as a powerful symbol of British heritage",)

        }
        2 ->{
            tourData = TourData(2,"LondonEye","The London Eye",5f,"Are you looking to take your London experience to new heights? Take a ride on Europe’s tallest cantilevered observation wheel in the heart of the city – a must-do on your London vacation.")
        }
        3 ->{
            tourData = TourData(3,"UberBoat","Uber Boat by Thames",4f,"How better to see the best of London’s landmarks than from the water!\n" +
                    "\n" + "If you are planning to visit London and looking for a unique way to explore the city, why not take a ride on an Uber Boat? Uber Boat, formerly known as Thames Clippers, is a river bus service that operates on the River Thames in London. With 23 piers across London, the service is a convenient and scenic way to travel around the city.")
        }
        4 ->{
            tourData = TourData(4,"Shard","The View from The Shard",3f,"From a 1970s office block to one of the world’s most distinguished buildings, ascend to London’s highest viewpoint at The View from The Shard.\n" +
                    "\n" + "Journey to a height of up to 800ft (244m); to the top of London’s tallest observation platform and premium visitor attraction, The View from The Shard. Formerly known as the London Bridge Tower")
        }
        5 ->{
            tourData = TourData(5,"Westminster","Westminster Abbey",3f,"Just a short walk from the Thames, Westminster Abbey is a must-see and a significant structure in British history. This beautiful gothic church is a UNESCO World Heritage Site popular with many visitors to London. Complete with paintings, stained glass windows, and other religious artifacts")

        }
        6 ->{
            tourData = TourData(6,"PaulCathedral","St Paul’s Cathedral",5f,"From royal weddings and state funerals to famous burials and more, St Paul’s Cathedral has played a major role in London’s history.\n" +
                    "\n" + "Situated near the River Thames, St Paul’s Cathedral is one of the capital’s most iconic buildings. ")
        }
        7 ->{
            tourData = TourData(7,"BigBen","Big Ben",5f,"Big Ben is probably the world's most famous clock. That iconic silhouette is instantly recognisable and is one of the most Instagrammed landmarks on the planet.  \n" +
                    "\n" + "Six monarchs and 41 prime ministers have come and gone since the bells first struck their now familiar music across Westminster.  ")

        }
        8 ->{
            tourData = TourData(8,"WindsorCastle","Windsor Castle",5f,"For over 1,000 years, Windsor Castle has been the English royal residence. This stunning castle, occupying 10.5 hectares/26.5 acres of land, is the largest and oldest occupied castle in the world.\n" +
                    "\n" + "William the Conqueror first made use of this vast space after the Battle of Hastings in 1066, crafting the castle out of wood in what was known as a motte-and-bailey style.")
        }
        9 ->{
            tourData = TourData(9,"GreatFireofLondon","The Monument to the Great Fire of London",5f,"Designed by Sir Christopher Wren (architect of the 51 city structures, including the iconic St. Paul's Cathedral), the monument was built to commemorate the Great Fire of London. It offers one of the best views of London while learning about an important moment in London's history. ")

        }
        10 ->{
            tourData = TourData(10,"MocoMuseum","Moco Museum London",3f,"Moco Museum London is a vibrant contemporary art gallery boasting works from world-famous artists including Banksy, Tracey Emin, KAWS, and more. Step inside the striking building and explore an awe-inspiring collection of modern artworks spotlighting both celebrated artists and emerging talent.")

        }
        else ->{
            tourData = TourData(1,"TowerLondon","Tower of London",4f,"The Tower of London is one of the world’s most famous fortresses and has seen service as a royal palace, prison, armory and even a zoo. Fortress, prison, royal mint, and now a tourist attraction. The Tower of London has seen many lives over the past centuries and persists to this day as a powerful symbol of British heritage",)
        }

    }
    return tourData
}

private fun tourData() =
    TourData(
        3,
        "Pizza",
        "Deep Dish Peperoni Pizza",
        4f,
        "a classic pizza option and is one of the most well-loved pizzas "
    )
