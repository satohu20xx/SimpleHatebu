package com.choilabo.simplehatebu.di.module

import com.choilabo.simplehatebu.ui.main.MainActivity
import dagger.Module


/**
 * Created by sato_shinichiro on 2018/01/12.
 */
@Module(includes = arrayOf(
        MainActivity.Module::class
))
abstract class ActivityModule