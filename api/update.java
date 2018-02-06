/**
 *
 * @api {POST} property/setValue Set Value
 * @apiName Set Value
 * @apiGroup property
 * @apiDescription
 * 重新设置属性值. 修改指定document(品牌地区) 指定property 指定字段 的值
 * 例如：修改eu_car,us_car中enable_product_server属性的value. 或修改eu_car中enable_product_server属性的tag.
 *
 * @apiHeader {String} Content-Type application/json;charset=UTF-8
 * @apiHeader {String} country country/area
 * @apiHeader {String} brand maison
 * @apiHeader {String} token user verification code
 *
 * @apiParam {String} ids  Document ID值. 修改多个, 需以英文逗号","分隔. (例: 59f84a4c9226bf18477afb29,59f84a4c9226bf18477afb29)
 * @apiParam {String} property  属性名. 修改sub_properties应将名称补全 并以英文点号"."分隔 (例: enable_product_server/billing_countries_enable.sub_properties.billing_countries_list)
 * @apiParam {String} column  字段名. (例: value/tag)
 * @apiParam {String} value  值. (例: TRUE/ENVIRONMENT)
 *
 * @apiSuccessExample {json} Success-Response:
 * HTTP/1.1 200 OK
 * {
 *      "success": true,
 *      "code": 0,
 *      "message": null,
 *      "data":{
 *      }
 * }
 *
 * @apiErrorExample {json} Error-Response-401:
 * HTTP/1.1 401 Unauthorized (token error)
 *
 * @apiErrorExample {json} Error-Response-403:
 * HTTP/1.1 403 Forbidden (request-header error)
 *
 * @apiErrorExample {json} Error-Response-404:
 * HTTP/1.1 404 Not Found (URL error)
 *
 * @apiErrorExample {json} Error-Response-415:
 * HTTP/1.1 415 Unsupported Media Type (param error)
 *
 */

/**
 *
 * @api {POST} property/addValue Add Value
 * @apiName Add Value
 * @apiGroup property
 * @apiDescription
 * 追加属性值. 针对type为list/map的属性, 为指定document(品牌地区) 指定property 指定字段 末尾追加值.
 * 例如：为eu_car,us_car中region_pref_language属性 追加值en=Enlish.
 *
 * @apiHeader {String} Content-Type application/json;charset=UTF-8
 * @apiHeader {String} country country/area
 * @apiHeader {String} brand maison
 * @apiHeader {String} token user verification code
 *
 * @apiParam {String} ids  Document ID值. 修改多个, 需以英文逗号","分隔 (例: 59f84a4c9226bf18477afb29,59f84a4c9226bf18477afb29)
 * @apiParam {String} property  属性名. 修改sub_properties应将名称补全 并以英文点号"."分隔 (例: region_pref_language/billing_countries_enable.sub_properties.billing_countries_list)
 * @apiParam {String} column  字段名. (例: value/tag)
 * @apiParam {String} value  值. 如需追加多个, 需以英文逗号","分隔 (例: en=Enlish,fr=French/ENVIRONMENT)
 *
 * @apiSuccessExample {json} Success-Response:
 * HTTP/1.1 200 OK
 * {
 *      "success": true,
 *      "code": 0,
 *      "message": null,
 *      "data":{
 *      }
 * }
 *
 * @apiErrorExample {json} Error-Response-401:
 * HTTP/1.1 401 Unauthorized (token error)
 *
 * @apiErrorExample {json} Error-Response-403:
 * HTTP/1.1 403 Forbidden (request-header error)
 *
 * @apiErrorExample {json} Error-Response-404:
 * HTTP/1.1 404 Not Found (URL error)
 *
 * @apiErrorExample {json} Error-Response-415:
 * HTTP/1.1 415 Unsupported Media Type (param error)
 *
 */

/**
 *
 * @api {POST} property/deleteValue Delete Value
 * @apiName Delete Value
 * @apiGroup property
 * @apiDescription
 * 删除属性值. 针对type为list/map的属性, 为指定document(品牌地区) 指定property 指定字段 删除值.
 * 例如：为eu_car,us_car中region_pref_language属性 删除值en=Enlish.
 *
 * @apiHeader {String} Content-Type application/json;charset=UTF-8
 * @apiHeader {String} country country/area
 * @apiHeader {String} brand maison
 * @apiHeader {String} token user verification code
 *
 * @apiParam {String} ids  Document ID值. 修改多个, 需以英文逗号","分隔. (例: 59f84a4c9226bf18477afb29,59f84a4c9226bf18477afb29)
 * @apiParam {String} property  属性名. 修改sub_properties应将名称补全 并以英文点号"."分隔 (例: region_pref_language/billing_countries_enable.sub_properties.billing_countries_list)
 * @apiParam {String} column  字段名. (例: value/tag)
 * @apiParam {String} value  值. 如需删除多个, 需以英文逗号","分隔 (例: en=Enlish,fr=French/ENVIRONMENT)
 *
 * @apiSuccessExample {json} Success-Response:
 * HTTP/1.1 200 OK
 * {
 *      "success": true,
 *      "code": 0,
 *      "message": null,
 *      "data":{
 *      }
 * }
 *
 * @apiErrorExample {json} Error-Response-401:
 * HTTP/1.1 401 Unauthorized (token error)
 *
 * @apiErrorExample {json} Error-Response-403:
 * HTTP/1.1 403 Forbidden (request-header error)
 *
 * @apiErrorExample {json} Error-Response-404:
 * HTTP/1.1 404 Not Found (URL error)
 *
 * @apiErrorExample {json} Error-Response-415:
 * HTTP/1.1 415 Unsupported Media Type (param error)
 *
 */

/**
 *
 * @api {POST} property/addPermissions Add Permissions
 * @apiName Add Permissions
 * @apiGroup property
 * @apiDescription
 * 为角色添加权限值. 为指定role 添加permission值
 * 例如：为eu_car,us_car的 CCA 添加权限 ORD_D
 *
 * @apiHeader {String} Content-Type application/json;charset=UTF-8
 * @apiHeader {String} country country/area
 * @apiHeader {String} brand maison
 * @apiHeader {String} token user verification code
 *
 * @apiParam {String} ids  Document ID值. 多个值以英文逗号","分隔. (例: 59f84a4c9226bf18477afb29,59f84a4c9226bf18477afb29)
 * @apiParam {String} permissions 权限. 如需添加多个, 需以英文逗号","分隔 (例: ORD_C,ORD_D)
 *
 * @apiSuccessExample {json} Success-Response:
 * HTTP/1.1 200 OK
 * {
 *      "success": true,
 *      "code": 0,
 *      "message": null,
 *      "data":{
 *      }
 * }
 *
 * @apiErrorExample {json} Error-Response-401:
 * HTTP/1.1 401 Unauthorized (token error)
 *
 * @apiErrorExample {json} Error-Response-403:
 * HTTP/1.1 403 Forbidden (request-header error)
 *
 * @apiErrorExample {json} Error-Response-404:
 * HTTP/1.1 404 Not Found (URL error)
 *
 * @apiErrorExample {json} Error-Response-415:
 * HTTP/1.1 415 Unsupported Media Type (param error)
 *
 */

/**
 *
 * @api {POST} property/deletePermissions Delete Permissions
 * @apiName Delete Permissions
 * @apiGroup property
 * @apiDescription
 * 为角色删除权限值. 为指定role 删除permission值
 * 例如：为eu_car,us_car的 CCA 删除权限 ORD_D
 *
 * @apiHeader {String} Content-Type application/json;charset=UTF-8
 * @apiHeader {String} country country/area
 * @apiHeader {String} brand maison
 * @apiHeader {String} token user verification code
 *
 * @apiParam {String} ids  Document ID值. 多个值以英文逗号","分隔. (例: 59f84a4c9226bf18477afb29,59f84a4c9226bf18477afb29)
 * @apiParam {String} permissions 权限. 如需删除多个, 需以英文逗号","分隔 (例: ORD_C,ORD_D)
 *
 * @apiSuccessExample {json} Success-Response:
 * HTTP/1.1 200 OK
 * {
 *      "success": true,
 *      "code": 0,
 *      "message": null,
 *      "data":{
 *      }
 * }
 *
 * @apiErrorExample {json} Error-Response-401:
 * HTTP/1.1 401 Unauthorized (token error)
 *
 * @apiErrorExample {json} Error-Response-403:
 * HTTP/1.1 403 Forbidden (request-header error)
 *
 * @apiErrorExample {json} Error-Response-404:
 * HTTP/1.1 404 Not Found (URL error)
 *
 * @apiErrorExample {json} Error-Response-415:
 * HTTP/1.1 415 Unsupported Media Type (param error)
 *
 */

/**
 *
 * @api {POST} property/queryProperties Query Properties
 * @apiName Query Properties
 * @apiGroup property
 * @apiDescription
 * 查询Documents. 查询指定条件的全部Documents
 *
 * @apiHeader {String} Content-Type application/json;charset=UTF-8
 * @apiHeader {String} country country/area
 * @apiHeader {String} brand maison
 * @apiHeader {String} token user verification code
 *
 * @apiParam {JSON} include 所包含查询条件. 多个值以英文逗号","分隔 (例: {brand:"CA,IW,MB",region:"EU",scope:"region_brand"})
 * @apiParam {JSON} excepted 所排出查询条件(非必须参数). 多个值以英文逗号","分隔 (例: {name:"ca_mtb,us_mtb"})
 *
 * @apiSuccessExample {json} Success-Response:
 * HTTP/1.1 200 OK
 * {
 *      "success": true,
 *      "code": 0,
 *      "message": null,
 *      "data":[
 *          {
 *              region:"EU",
 *              brand:"CA",
 *              scope:"region_brand",
 *              name:"eu_car",
 *              properties{
 *                  ...
 *              }
 *          },
 *          {
 *              region:"EU",
 *              brand:"IW",
 *              scope:"region_brand",
 *              name:"eu_iwc",
 *              properties{
 *                  ...
 *              }
 *          },
 *          ...
 *      ]
 * }
 *
 * @apiErrorExample {json} Error-Response-401:
 * HTTP/1.1 401 Unauthorized (token error)
 *
 * @apiErrorExample {json} Error-Response-403:
 * HTTP/1.1 403 Forbidden (request-header error)
 *
 * @apiErrorExample {json} Error-Response-404:
 * HTTP/1.1 404 Not Found (URL error)
 *
 * @apiErrorExample {json} Error-Response-415:
 * HTTP/1.1 415 Unsupported Media Type (param error)
 *
 */

/**
 *
 * @api {POST} property/queryRoles Query Roles
 * @apiName Query Roles
 * @apiGroup property
 * @apiDescription
 * 查询角色. 查询指定条件的全部角色
 *
 * @apiHeader {String} Content-Type application/json;charset=UTF-8
 * @apiHeader {String} country country/area
 * @apiHeader {String} brand maison
 * @apiHeader {String} token user verification code
 *
 * @apiParam {JSON} include 所包含查询条件. 多个值以英文逗号","分隔 (例: {brand:"CA,IW,MB",code:"CCA"})
 * @apiParam {JSON} excepted 所排出查询条件(非必须参数). 多个值以英文逗号","分隔 (例: {region:"EU"})
 *
 * @apiSuccessExample {json} Success-Response:
 * HTTP/1.1 200 OK
 * {
 *      "success": true,
 *      "code": 0,
 *      "message": null,
 *      "data":[
 *          {
 *              region:"US",
 *              brand:"CA",
 *              code:"CCA",
 *              name:"Customer Care Agent",
 *              type:"normal",
 *              permissions:[
 *                  ...
 *              ]
 *          },
 *          {
 *              region:"US",
 *              brand:"MB",
 *              code:"CCA",
 *              name:"Customer Care Agent",
 *              type:"normal",
 *              permissions:[
 *                  ...
 *              ]
 *          },
 *          ...
 *      ]
 * }
 *
 * @apiErrorExample {json} Error-Response-401:
 * HTTP/1.1 401 Unauthorized (token error)
 *
 * @apiErrorExample {json} Error-Response-403:
 * HTTP/1.1 403 Forbidden (request-header error)
 *
 * @apiErrorExample {json} Error-Response-404:
 * HTTP/1.1 404 Not Found (URL error)
 *
 * @apiErrorExample {json} Error-Response-415:
 * HTTP/1.1 415 Unsupported Media Type (param error)
 *
 */