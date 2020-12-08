package com.android.example.thirdlib.hilt;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @author:無忌
 * @date:2020/12/7
 * @description:
 */
@Qualifier
@Retention(RetentionPolicy.SOURCE)
@interface BindElectricEngine {
}
