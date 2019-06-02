package chat.rocket.luk.sortingandgrouping.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.sortingandgrouping.presentation.SortingAndGroupingView
import chat.rocket.luk.sortingandgrouping.ui.SortingAndGroupingBottomSheetFragment
import dagger.Module
import dagger.Provides

@Module
class SortingAndGroupingBottomSheetFragmentModule {

    @Provides
    @PerFragment
    fun sortingAndGroupingView(frag: SortingAndGroupingBottomSheetFragment): SortingAndGroupingView =
        frag
}