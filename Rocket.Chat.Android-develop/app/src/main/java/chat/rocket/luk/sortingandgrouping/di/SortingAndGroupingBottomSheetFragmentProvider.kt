package chat.rocket.luk.sortingandgrouping.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.sortingandgrouping.ui.SortingAndGroupingBottomSheetFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SortingAndGroupingBottomSheetFragmentProvider {

    @ContributesAndroidInjector(modules = [SortingAndGroupingBottomSheetFragmentModule::class])
    @PerFragment
    abstract fun provideSortingAndGroupingBottomSheetFragment(): SortingAndGroupingBottomSheetFragment

}