package com.nk

class FunctionalProgramming {


    fun findByTitle(title: String, films: List<Film>): List<Film> {
        val result = mutableListOf<Film>()

        // Closure
        val add = fun(movie: Film) {
            result.add(movie)
        }

        for (film in films) {
            val addIf = addIf(::matches, film, title, add)
            addIf(film)
        }
        return result
    }

    private fun addIf(predicate: (Film, String) -> Boolean, film: Film,
                      title: String,
                      add: (Film) -> Unit) : (Film) -> Unit {

        if (predicate(film, title)) {
            return add
        }else {
            return fun(film:Film){}
        }
    }


}
data class Film(var title: String)

fun matches(film: Film, title: String) = film.title == title
fun title(film: Film) = film.title