package com.sunnyschool.simpleapp

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Copyright Digitain 2020
 * Created by Narek Hayrapetyan on 16-11-2020.
 * E-Mail: narek.hayrapetyan@digitain.com
 */
object FragmentUtil {
    fun showFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        add: Boolean = true,
        @IdRes layoutId:Int = R.id.fragment_container) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val findFragmentById = fragmentManager.findFragmentById(fragment.id)
        if (findFragmentById != null && findFragmentById.isAdded) return

        if (add) {
            transaction.add(layoutId, fragment, fragment.javaClass.name)
            transaction.addToBackStack(null)
        } else {
            transaction.replace(layoutId, fragment, fragment.javaClass.name)
        }
        transaction.commit()
    }

    fun removeFragment(fragmentManager: FragmentManager, fragment: Fragment? = null) {
        if (fragment == null){
            fragmentManager.popBackStack()
            return
        }
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val findFragmentById = fragmentManager.findFragmentById(fragment.id)

        if (findFragmentById != null && findFragmentById.isAdded) {
            transaction.remove(findFragmentById).commit()
        }
    }
}