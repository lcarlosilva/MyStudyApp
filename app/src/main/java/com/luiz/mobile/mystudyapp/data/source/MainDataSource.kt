package com.luiz.mobile.mystudyapp.data.source

import android.os.Build
import com.luiz.mobile.mystudyapp.domain.repository.MainRepository

class MainDataSource : MainRepository {
    override fun getDevicesInfos(result: (infosDevices: String) -> Unit) {
        result.invoke(
                "Brand: ${Build.BRAND}\n" +
                        "Product: ${Build.PRODUCT}\n" +
                        "Hardware: ${Build.HARDWARE}\n" +
                        "Device: ${Build.DEVICE}\n" +
                        "Manufacture: ${Build.MANUFACTURER}\n" +
                        "Model: ${Build.MODEL}\n" +
                        "Security Patch: ${Build.VERSION.SECURITY_PATCH}\n" +
                        "Release: ${Build.VERSION.RELEASE}\n" +
                        "SDK: ${Build.VERSION.SDK_INT}\n" +
                        "Display: ${Build.DISPLAY}\n" +
                        "Board: ${Build.BOARD}\n" +
                        "Code Name: ${Build.VERSION.CODENAME}"
        )
    }
}