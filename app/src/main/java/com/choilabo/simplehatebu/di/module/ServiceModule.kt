package com.choilabo.simplehatebu.di.module

import com.choilabo.simplehatebu.service.GetHotentoryJobService
import dagger.Module


/**
 * Created by sato_shinichiro on 2018/01/12.
 */
@Module(includes = arrayOf(
        GetHotentoryJobService.Module::class
))
abstract class ServiceModule