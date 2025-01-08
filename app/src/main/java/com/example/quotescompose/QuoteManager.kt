package com.example.quotescompose

data class Quote(val quote: String, val author: String)

object QuoteManager {
    val quoteList = mutableListOf<Quote>()

    fun initializeQuotes() {
        repeat(10) {
            addQuote(Quote("Quote $it", "Author $it"))
        }
    }

    fun addQuote(quote: Quote) {
        quoteList.add(quote)
    }

    fun searchQuotes(searchParam: String): MutableList<Quote> {
        return quoteList.filter {
            it.quote.contains(searchParam, ignoreCase = true) || it.author.contains(
                searchParam,
                ignoreCase = true
            )
        }.toMutableList()
    }

}
