package com.sai.utils

import android.Manifest

const val LogTag = "---Log-"



// 页面状态

const val state_empty   = 0x1
const val state_loading = 0x2
const val state_error   = 0x3

// 运行时权限 需要申请的
// 联系人，相机； 后台位置（29），精确位置，粗略位置； 读取外部储存，写入外部储存

const val permission_read_contacts   = Manifest.permission.READ_CONTACTS
const val permission_camera          = Manifest.permission.CAMERA
const val permission_location_back   = Manifest.permission.ACCESS_BACKGROUND_LOCATION
const val permission_location_fine   = Manifest.permission.ACCESS_FINE_LOCATION
const val permission_location_coarse = Manifest.permission.ACCESS_COARSE_LOCATION
const val permission_storage_read    = Manifest.permission.READ_EXTERNAL_STORAGE
const val permission_storage_write   = Manifest.permission.WRITE_EXTERNAL_STORAGE
