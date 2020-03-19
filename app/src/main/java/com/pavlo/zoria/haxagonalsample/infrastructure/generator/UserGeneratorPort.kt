package com.pavlo.zoria.haxagonalsample.infrastructure.generator

import com.pavlo.zoria.haxagonalsample.domain.model.User
import com.pavlo.zoria.haxagonalsample.domain.port.UserPort
import com.pavlo.zoria.haxagonalsample.infrastructure.generator.model.UserEntity
import javax.inject.Inject
import kotlin.random.Random

class UserGeneratorPort @Inject constructor(): UserPort {

    override fun getAll(): List<User> {
        return generateUsers()
    }

    override fun getUserById(id: String): User? {
        return generateUser()
    }

    private fun generateUsers(): MutableList<User> {
        val nextInt = Random.nextInt(15)
        val users = mutableListOf<User>()
        for (i in 0..nextInt) {
            users.add(generateUser())
        }
        return users
    }

    private fun generateUser(): User {
        return UserEntity(0, getRandomName(), getRandomImage())
    }

    private fun getRandomImage(): String {
        return randomFromList(profileUrls)
    }

    private fun getRandomName(): String {
        val randomName = randomFromList(names)
        val randomSurname = randomFromList(surnames)
        return "$randomName $randomSurname"
    }

    private fun randomFromList(list: ArrayList<String>) = list[Random.nextInt(list.size - 1)]

    private val names = arrayListOf(
        "John",
        "Michael",
        "Marry",
        "Siusan",
        "Petr",
        "Vasyl",
        "Ivan",
        "Iohan",
        "Pavlo",
        "Oleksandr"
    )
    private val surnames = arrayListOf(
        "Book",
        "Table",
        "Tailor",
        "Tree",
        "House",
        "Wife",
        "Baker",
        "Byker",
        "Window",
        "Door"
    )
    private val profileUrls = arrayListOf(
        "https://content.api.news/v3/images/bin/a6923adbc7bece73803221613f410782",
        "https://assets.entrepreneur.com/content/3x2/2000/20190502194704-ent19-june-editorsnote.jpeg",
        "https://studio21.ru/wp-content/uploads/2019/12/malbek-e1576755686453.jpg",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS3NrFym46ufX-aK_iSkVSIcJSI6pCHFPVHy0MJ7hRAU5BH5eYi",
        "https://static.independent.co.uk/s3fs-public/thumbnails/image/2018/03/18/15/billgates.jpg",
        "https://www.biography.com/.image/ar_8:10%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cg_faces:center%2Cq_auto:good%2Cw_620/MTY4MzU0NDMzMjc5NzMxNjcw/julian-castro-sergio-floresbloomberg-via-getty-images-square.jpg",
        "https://thebodyisnotanapology.com/wp-content/uploads/2018/02/pexels-photo-459947.jpg",
        "https://miro.medium.com/max/2560/1*gBQxShAkxBp_YPb14CN0Nw.jpeg",
        "https://api.time.com/wp-content/uploads/2017/12/terry-crews-person-of-year-2017-time-magazine-2.jpg",
        "https://assets.change.org/photos/0/kx/ah/hjkxAHxAKbytEHg-800x450-noPad.jpg?1560309500",
        "https://img.buzzfeed.com/buzzfeed-static/static/2019-11/8/20/campaign_images/233a50525782/trumps-allies-have-been-attacking-the-person-they-2-1901-1573245212-0_dblbig.jpg",
        "https://i1.wp.com/highlysensitiverefuge.com/wp-content/uploads/2018/08/what-is-highly-sensitive-person-meaning.jpg?resize=1150%2C701&ssl=1",
        "https://image.cnbcfm.com/api/v1/image/106069136-1565284193572gettyimages-1142580869.jpeg?v=1576531407&w=1400&h=950",
        "https://resize.indiatvnews.com/en/resize/newbucket/715_-/2019/11/virat-kohli-1574240907.jpg"
    )

}