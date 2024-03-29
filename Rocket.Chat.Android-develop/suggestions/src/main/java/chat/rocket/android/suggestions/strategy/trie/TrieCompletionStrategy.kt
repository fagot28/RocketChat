package chat.rocket.luk.suggestions.strategy.trie

import chat.rocket.luk.suggestions.model.SuggestionModel
import chat.rocket.luk.suggestions.strategy.CompletionStrategy
import chat.rocket.luk.suggestions.strategy.trie.data.Trie

class TrieCompletionStrategy : CompletionStrategy {
    private val items = mutableListOf<SuggestionModel>()
    private val trie = Trie()

    override fun getItem(prefix: String, position: Int): SuggestionModel {
        val item: SuggestionModel
        if (prefix.isEmpty()) {
            item = items[position]
        } else {
            item = autocompleteItems(prefix)[position]
        }
        return item
    }

    override fun autocompleteItems(prefix: String): List<SuggestionModel> {
        return trie.autocompleteItems(prefix)
    }

    override fun addAll(list: List<SuggestionModel>) {
        items.addAll(list)
        list.forEach {
            trie.insert(it)
        }
    }

    override fun addPinned(list: List<SuggestionModel>) {
    }

    override fun size() = items.size
}
