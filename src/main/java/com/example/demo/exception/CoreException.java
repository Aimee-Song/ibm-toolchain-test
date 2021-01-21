
package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by daisy on 14-4-4.
 */
@ControllerAdvice
@Slf4j
public class CoreException extends Exception {

    public enum ExceptionType {
        DEFAULT_FAIL_EXCEPTION(1, ""),
        CSRF_VALIDATE_FAILED(801, "csrf_validate_failed"),


        // common
        PARAMETER_INVALID(900, "param_error"),
        RESOURCE_NO_FOUND(404, "resource_not_found"),
        CONTENT_INCLUDE_SENSITIVE_WORDS(901, "content_include_sensitive_words"),
        IMAGE_BIG_THAN_5M(902, "image_big_than_5m"),
        CAPTCHA_ERROR(903, "j_captcha_error"),
        OPERATING_FREQUENCY_FAST(904, "operating_frequency_fast"),
        NETWORK_CONNECTION_ERROR(905, "network_connection_error"),
        CONTENT_EQUALS_RESERVED_WORDS(906, "content_equals_reserved_words"),
        OPERATION_NEED_CAPTCHA(907, "operation_need_captcha"),
        GLOBAL_KEY_INVALID(908, "global_key_invalid"),
        CAPTCHA_NOT_EMPTY(909, "j_captcha_not_empty"),
        DATE_FORMAT_INVALID(910, "date_format_invalid"),
        DATE_START_FORMAT_INVALID(911, "date_start_format_invalid"),
        DATE_END_FORMAT_INVALID(912, "date_end_format_invalid"),
        IMPORT_PROJECT_FAILED(913, "import_project_failed"),
        METHOD_NOW_ALLOWED(914, "method_not_allowed"),
        TEAM_CAN_NOT_CLEAR_NORMAL_USER(914, "team_can_not_clear_normal_user"),
        SIGN_ERROR(915, "sign_error"),
        ORIGIN_ERROR(916, "origin_error"),
        DATE_ERROR(917, "date_error"),
        COUNT_LIMIT_ERROR(918, "count_limit_error"),

        // user 相关以 10 开头
        USER_NOT_LOGIN(1000, "user_not_login"),
        USER_NOT_EXISTS(1001, "user_not_exists"),
        USER_NOT_FRIENDS(1002, "user_not_friends"),
        USER_IS_FRIEND(1003, "user_is_friend"),
        USER_REGISTER_FAILED(1004, "user_register_failed"),
        USER_ACTIVATE_FAILED(1005, "user_activate_failed"),
        USER_RESET_PASSWORD_FAILED(1006, "user_reset_password_failed"),
        USER_PASSWORD_INCONSISTENT(1007, "user_password_inconsistent"),
        USER_OLD_PASSWORD_ERROR(1008, "user_old_password_error"),
        USER_IS_BLOCK(1009, "user_is_block"),

        USER_EMAIL_NOT_EXISTS(1010, "user_email_not_exists"),
        USER_OAUTH_TIMEOUT(1011, "user_oauth_timeout"),
        USER_OAUTH_ACCESS_DENIED(1012, "user_oauth_access_denied"),
        USER_OAUTH_NO_INFO(1013, "user_oauth_no_info"),
        USER_OAUTH_NO_ACCOUNT(1014, "user_oauth_no_account"),
        USER_ALREADY_EXISTS(1015, "user_already_exists"),
        USER_IS_IN_BLACK_LIST(1016, "user_is_in_black_list"),
        USER_OAUTH_ALREADY_BOUND(1017, "user_oauth_already_bound"),
        USER_NOT_GET_REQUEST_TOO_MANY(1018, "user_not_get_request_too_many"),
        USER_AVATAR_TOO_LARGE(1019, "user_avatar_too_large"),
        UPLOAD_AVATAR_ERROR(1020, "upload_avatar_error"),
        USER_AVATAR_TOO_BIG(1021, "user_avatar_too_big"),
        USER_AVATAR_TOO_SMALL(1022, "user_avatar_too_small"),
        USER_AVATAR_ERROR_FILE(1023, "user_avatar_error_file"),
        USER_ACTIVE_LINK_ERROR(1024, "user_active_link_error"),
        USER_EMAIL_ALREADY_REGISTER(1025, "user_email_already_register"),
        USER_PHONE_ALREADY_BOUND(1026, "user_phone_already_bound"),
        USER_PASSWORD_NOT_EMPTY(1027, "user_password_not_empty"),
        USER_PASSWORD_TOO_LONG(1028, "user_password_too_long"),
        USER_LOGIN_STATUS_EXPIRED(1029, "user_login_status_expired"),
        USER_PHONE_MODIFY_TIME_OVER(1030, "user_phone_modify_time_over"),
        USER_PHONE_VALIDATE_CODE_ERROR(1031, "user_phone_validate_code_error"),
        USER_PHONE_NOT_VALIDATED(1032, "user_phone_not_validated"),
        USER_PHONE_ERROR(1033, "user_phone_error"),
        USER_PHONE_NOT_BOUND(1034, "user_phone_not_bound"),
        USER_NEED_ACTIVATE(1035, "user_need_activate"),
        USER_PASSWORD_ALREADY_SET(1036, "user_password_already_set"),
        USER_EMAIL_NEED_ACTIVATE(1037, "user_email_need_activate"),
        USER_GLOBAL_KEY_ALREADY_REGISTER(1038, "user_global_key_already_register"),
        USER_NAME_ALREADY_REGISTER(1039, "user_name_already_register"),
        USER_NAME_PINYIN_UPDATE_FAIL(1040, "user_name_pinyin_update_fail"),
        USER_NAME_GENERATE_ERROR(1041, "user_name_generate_error"),
        USER_IS_REMOVED_IN_TEAM(1042, "user_is_removed_in_team"),
        USER_TWO_FACTOR_NOT_ENABLE(1042, "user_two_factor_not_enable"),
        USER_PHONE_NOT_EXISTS(1043, "user_phone_not_exists"),
        USER_EWECHAT_ALREADY_BOUND(1044, "user_ewechat_already_bound"),
        USER_DID_NOT_BIND_EWECHAT(1045, "user_did_not_bind_ewechat"),
        USER_WORK_WECHHAT_INFO_FAIL(1046, "user_work_wechhat_info_fail"),
        USER_PASSWORD_ERROR(1046, "user_password_error"),

        PRIVATE_TOKEN_IS_OPENED(1080, "private_token_is_opened"),
        PRIVATE_TOKEN_IS_CLOSED(1081, "private_token_is_closed"),

        PERSONAL_ACCESS_TOKEN_DESCRIPTION_NOT_EMPTY(1082, "personal_access_token_description_not_empty"),
        PERSONAL_ACCESS_TOKEN_DESCRIPTION_TOO_LONG(1083, "personal_access_token_description_too_long"),
        PERSONAL_ACCESS_TOKEN_DESCRIPTION_DUPLICATE(1084, "personal_access_token_description_duplicate"),
        PERSONAL_ACCESS_TOKEN_NOT_EXISTS(1085, "personal_access_token_not_exists"),

        FORCE_ENABLE_TWOFA_BY_TEAM(1086, "force_enable_twofa_by_team"),
        USER_ALREADY_ACTIVE(1088, "user_already_active"),
        FORCE_ENABLE_TWOFA_BY_MANAGER(1089, "force_enable_twofa_by_manager"),
        FORCE_ENABLE_TWOFA_BY_STAFF(1090, "force_enable_twofa_by_staff"),

        USER_OAUTH_ERROR(1097, "user_oauth_error"),

        // project 相关以 11 开头
        PROJECT_NOT_EXIST(1100, "project_not_exists"),
        PROJECT_MEMBER_EXISTS(1101, "project_member_exists"),
        PROJECT_MEMBER_NOT_EXISTS(1102, "project_member_not_exists"),
        PROJECT_NAME_EXISTS(1103, "project_name_exists"),
        PROJECT_ALREADY_STARS(1106, "project_already_stars"),
        PROJECT_ALREADY_WATCH(1107, "project_already_watch"),
        PROJECT_OWNER_CANNOT_QUIT(1109, "project_owner_can_not_quit"),
        PROJECT_MEMBER_OVER(1110, "project_member_over"),
        PROJECT_FILE_SPACE_OVER(1111, "project_file_space_over"),
        QUIT_PROJECT_FAIL_HAVE_PROGRESS_TASK(1112, "quit_project_fail_have_progress_task"),
        DELETE_MEMBER_FAIL_HAVE_PROGRESS_TASK(1113, "delete_member_fail_have_progress_task"),
        TASK_STATUS_PARAM_ERROR(1115, "task_status_param_error"),
        PROJECT_NAME_CONFLICT(1117, "project_name_conflict"),
        PROJECT_ICON_TOO_LARGE(1118, "project_icon_too_large"),
        UPDATE_PROJECT_ICON_ERROR(1119, "update_project_icon_error"),
        PROJECT_ICON_ERROR(1120, "project_icon_error"),
        PROJECT_IMAGE_BIG_THAN_10M(1121, "project_image_big_than_10m"),
        PROJECT_IMAGE_UPLOAD_ERROR(1122, "project_image_upload_error"),
        PROJECT_NOT_MEMBER(1123, "project_not_member"),
        PROJECT_PUBLIC_DENY_PROJECT_PERMISSION(1124, "project_public_deny_project_permission"),
        PROJECT_ALREADY_ARCHIVED(1125, "project_already_archived"),
        PROJECT_NOT_ARCHIVED(1126, "project_not_archived"),
        PROJECT_ARCHIVE_ERROR(1127, "project_archive_error"),
        PROJECT_UNARCHIVE_ERROR(1128, "project_unarchive_error"),
        PROJECT_UNARCHIVE_NAME_DUPLICATED(1129, "project_unarchive_name_duplicated"),
        PROJECT_IS_ARCHIVED(1130, "project_is_archived"),
        ALIAS_INVALID(1131, "alias_invalid"),
        PROJECT_OWNER_ONLY(1132, "project_owner_only"),
        PROJECT_FILE_ZIP_DOWNLOAD_FAILED(1133, "project_file_zip_download_failed"),
        PROJECT_FOLDER_NAME_DUPLICATE(1134, "project_folder_name_duplicate"),
        PROJECT_FOLDER_CANNOT_MV_TO_SUBFOLDER(1135, "project_folder_cannot_mv_to_subfolder"),
        PROJECT_FOLDERS_REQUIRED(1136, "project_folders_required"),
        PROJECT_DELETE_NO_EMPTY_FOLDER(1137, "project_delete_no_empty_folder"),
        PROJECT_TYPE_INVALID(1138, "project_type_invalid"),
        PROJECT_VCS_TYPE_INVALID(1139, "project_vcs_type_invalid"),
        PROJECT_FILE_NAME_DUPLICATE(1140, "project_file_name_duplicate"),
        PROJECT_FILE_MOVE_INTO_SELF(1141, "project_file_move_into_self"),
        PROJECT_FILE_ALL_IN_TARGET_FOLDER(1142, "project_file_all_in_target_folder"),
        PROJECT_FILE_RETAIN_CYCLE(1143, "project_file_retain_cycle"),
        PROJECT_QUICK_FOLDER_OPERATION_DISALLOW(1144, "project_quick_folder_operation_disallow"),
        PROJECT_FILE_COMMENT_TOO_LONG(1, "project_file_comment_too_long"),
        PROJECT_NAME_ERROR(1, "project_name_error"),
        IMPORT_URL_INVALID(1, "import_url_invalid"),
        PROJECT_FILE_NAME_TOO_LONG(1147, "project_file_name_too_long"),
        TARGET_PROJECT_NOT_EXIST(1148, "target_project_not_exists"),
        PROJECT_START_DATE_ERROR(1148, "project_start_date_error"),
        PROJECT_END_DATE_ERROR(1149, "project_end_date_error"),
        PROJECT_END_DATE_BEFORE_START_DATE(1150, "project_end_date_before_start_date"),
        PROJECT_DEMO_NAME_EXISTS(1151, "project_demo_name_exists"),
        GENERATE_KEY_ERROR(1152, "generate_key_error"),
        DEPOT_IMPORT_FAILED(1153, "depot_import_failed"),
        ADD_OUTER_KEY_FAILED(1154, "add_outer_key_failed"),
        PROJECT_DISPLAY_NAME_IS_EMPTY(1160, "project_display_name_is_empty"),
        PROJECT_DISPLAY_NAME_EXISTS(1161, "project_display_name_exists"),
        PROJECT_DISPLAY_NAME_ERROR(1163, "project_display_name_error"),
        PROJECT_CREATION_ERROR(1164, "project_creation_failed"),
        PROJECT_RESOURCE_CODE_AMOUNT_ERROR(1165, "project_resource_code_amount_error"),
        PROJECT_RESOURCE_CODE_IS_USED(1166, "project_resource_code_is_used"),
        PROJECT_RESOURCE_INSERT_EMPTY(1167, "project_resource_insert_empty"),
        PROJECT_DEMO_CREATE_FAILURE(1169, "project_demo_create_failure"),
        PROJECT_DEMO_RESET_LIMIT(1170, "project_demo_reset_limit"),
        PROJECT_TEMPLATE_NOT_EXIST(1171, "project_template_not_exist"),


        // depot 相关的以 12 开头
        DEPOT_NOT_EXIST(1200, "depot_not_exists"),
        DIFF_TOO_LARGE(1201, "diff_too_large"),
        DEPOT_FORKED(1202, "depot_forked"),
        PULL_REQUEST_NOT_FOUND(1203, "pull_request_not_found"),
        DEPOT_HAS_NO_COMMIT(1204, "depot_has_no_commit"),
        CAN_NOT_PULL_REQUEST(1205, "can_not_pull_request"),
        PULL_REQUEST_EXIST(1206, "pull_request_exists"),
        PUBLIC_KEY_EXIST(1207, "public_key_exists"),
        PUBLIC_KEY_FORMAT_ERROR(1217, "public_key_format_error"),
        CAN_NOT_FORK_PRIVATE(1208, "cant_not_fork_private"),
        PATH_NOT_FOUND(1209, "path_not_found"),
        COMPARE_COMMIT_1_NOT_EXIST(1210, "compare_commit_1_not_exist"),
        COMPARE_COMMIT_2_NOT_EXIST(1211, "compare_commit_2_not_exist"),
        COMMIT_NOT_EXISTS(1212, "commit_not_exists"),
        TARGET_DEPOT_NOT_EXISTS(1213, "target_depot_not_exists"),
        BRANCH_NOT_EXISTS(1214, "branch_not_exists"),
        FILE_NOT_EXISTS(1215, "file_not_exists"),
        IS_NOT_IN_HEAD(1216, "is_not_in_head"),
        FILE_EXISTS(1217, "file_exists"),
        FILE_NAME_ERROR(1218, "file_name_error"),
        BRANCH_NAME_ERROR(1219, "branch_name_error"),
        TAG_NAME_ERROR(1220, "tag_name_error"),
        PROTECTED_BRANCH_EXIST(1221, "protected_branch_exist"),
        BRANCH_OR_TAG_EXIST(1222, "branch_or_tag_exist"),
        DEPOT_NOT_EMPTY(1223, "depot_not_empty"),
        DEPOT_IS_IMPORTING(1224, "depot_is_importing"),
        MERGE_REQUEST_EXIST(1225, "merge_request_exits"),
        CAN_NOT_MERGE_REQUEST(1226, "can_not_merge_request"),
        MERGE_REQUEST_NOT_FOUND(1227, "merge_request_not_found"),
        FILE_NOT_TEXT(1228, "file_not_text"),
        SRC_BRANCH_NOT_EXIST(1229, "src_branch_not_exist"),
        DES_BRANCH_NOT_EXIST(1230, "des_branch_not_exist"),
        BRANCH_NAME_NOT_EMPTY(1231, "branch_name_not_empty"),

        CAN_NOT_PARSE_UPLOAD_FILES(1232, "can_not_parse_upload_files"),
        SINGLE_FILE_EXCEEDED_SIZE_LIMIT(1233, "single_file_exceeded_size_limit"),
        INVALID_ENCODING(1234, "invalid_encoding"),
        MERGE_REQUEST_PROTECTED_BRANCH_NOT_ENABLE(1235, "merge_request_protected_branch_not_enable"),
        MERGE_REQUEST_PROTECTED_BRANCH_ENABLE_WITHOUT_ADMIN(1236, "merge_request_protected_branch_enable_without_admin"),
        MERGE_REQUEST_PROTECTED_BRANCH_ENABLE_WITH_ADMIN(1237, "merge_request_protected_branch_enable_with_admin"),
        PUBLIC_KEY_EXPIRATION_DATE_FORMAT_ERROR(1238, "public_key_expiration_date_format_error"),
        PUBLIC_KEY_NOT_FOUND(1239, "public_key_not_found"),

        REFERENCE_CANNOT_ADD_SELF(1240, "reference_cannot_add_self"),

        LINE_NOTE_PARENT_ERROR(1241, "line_note_parent_error"),

        CREATE_MERGE_REQUEST_NO_HEAD(1242, "create_merge_request_no_head"),

        LINE_NOTE_FAILED(1244, "line_note_failed"),
        INIT_DEPOT_BY_TEMPLATE(1245, "init_depot_by_template"),

        TAG_POINT_NOT_EXIST(1250, "tag_point_not_exist"),
        START_POINT_NOT_EXIST(1251, "start_point_not_exist"),

        DEPOT_SIZE_IS_TOO_LARGE(1252, "depot_size_is_too_large"),

        COMMIT_CONTENT_CANNOT_BE_EMPTY(1253, "commit_content_cannot_be_empty"),

        WEBHOOK_IS_NOT_EXIST(1254, "webhook_is_not_exist"),
        FAIL_TO_PUSH(1255, "fail_to_push_commit"),
        BRANCH_DENY_FORCE_PUSH(1256, "branch_deny_force_push"),

        DEPOT_MAINTAINING(1257, "depot_maintaining"),
        DEPOT_DISABLED(1258, "depot_disabled"),
        DEPOT_CANNOT_USE(1259, "depot_cannot_use"),

        PULL_REQUEST_STATUS_IS_NOT_CANMERGE(1260, "pull_request_status_is_not_can_merge"),
        PULL_REQUEST_STATUS_IS_CANNOTMERGE(1261, "pull_request_status_is_can_not_merge"),
        MERGE_REQUEST_STATUS_IS_NOT_CANMERGE(1262, "merge_request_status_is_not_can_merge"),
        MERGE_REQUEST_STATUS_IS_CANNOTMERGE(1263, "merge_request_status_is_can_not_merge"),

        MERGE_REQUEST_ALREADY_MERGED(1264, "merge_request_already_merged"),
        MERGE_REQUEST_ALREADY_CANCEL(1265, "merge_request_already_cancel"),
        MERGE_REQUEST_ALREADY_REFUSED(1266, "merge_request_already_refused"),
        MERGE_REQUEST_IS_MERGING(1267, "merge_request_is_merging"),
        BRANCH_HAS_OPEN_MERGE_REQUEST(1268, "branch_has_open_merge_request"),
        DEFAULT_BRANCH(1269, "default_branch"),

        MERGE_REQUEST_TITLE_NOT_EMPTY(1270, "pull_request_title_not_empty"),
        MERGE_REQUEST_TITLE_TOO_LONG(1271, "pull_request_title_too_long"),
        MERGE_REQUEST_DENY_REVIEW_GOOD_TO_SELF(1272, "merge_request_deny_review_good_to_self"),
        MERGE_REQUEST_DENY_CANCEL_REVIEW_GOOD_TO_SELF(1273, "merge_request_deny_cancel_review_good_to_self"),

        DIFF_IS_NULL(1281, "diff_is_null"),

        PARENT_FILE_HAS_LOCKED(1283, "parent_file_has_locked"),
        CHILD_FILE_HAS_LOCKED(1284, "child_file_has_locked"),
        FILE_ALREADY_LOCKED(1285, "file_already_locked"),
        FILE_NOT_LOCKED(1286, "file_not_locked"),
        LOCK_FILE_FAILED(1287, "lock_file_failed"),
        UNLOCK_FILE_FAILED(1288, "unlock_file_failed"),
        HAS_FILE_LOCKED(1289, "has_file_locked"),

        PUBLIC_KEY_IS_EMPTY(1291, "public_key_not_empty"),
        PAGES_ALREADY_OPENED(1292, "pages_already_opened"),
        PAGES_DOMAIN_INVALID(1293, "pages_domain_invalid"),
        PAGES_DOMAIN_EXIST(1294, "pages_domain_exist"),
        PAGES_DOMAIN_TOO_MANY(1295, "pages_domain_too_many"),
        PAGES_NOT_OPENED(1292, "pages_not_opened"),
        PAGES_BRANCH_INVALID(1296, "pages_branch_invalid"),
        PAGES_INTERNAL_ERROR(1297, "pages_internal_error"),
        PAGES_DEPLOY_BRANCH_NOT_FOUND(1298, "pages_deploy_branch_not_found"),
        MERGE_REQUEST_SQUASH_FAILED(1299, "merge_request_squash_failed"),


        // file相关的以 13 开头
        FILE_DIRECTORY_NAME_NOT_EMPTY(1301, "file_directory_name_not_empty"),
        FILE_DIRECTORY_IS_EXIST(1302, "file_directory_is_exist"),
        FILE_NOT_EMPTY(1303, "file_not_empty"),
        FILE_NOT_EXIST(1304, "file_not_exist"),
        FILE_DIRECTORY_NOT_EXIST(1305, "file_directory_not_exist"),
        FILE_DIRECTORY_NOT_EMPTY(1306, "file_directory_not_empty"),
        FILE_NAME_CONFLICT(1307, "file_name_conflict"),
        FILE_HISTORY_REMARK_TOO_LONG(1308, "file_history_remark_too_long"),
        FILE_HISTORY_TYPE_ERROR(1309, "file_history_type_error"),
        FILE_NOT_EXIST_OR_DELETED(1310, "file_not_exist_or_deleted"),
        FILE_UPLOAD_AUTH_INVALID(1311, "file_upload_auth_invalid"),
        UNSUPPORTED_FILE_NAME_CHARS(1312, "unsupported_file_name_chars"),
        FILE_UPLOAD_TOKEN_GENERATE_FAILED(1313, "file_upload_token_generate_failed"),

        //permissions相关以 14 开头
        PERMISSION_DENIED(1400, "permission_denied"),
        PASSWORD_ERROR(1401, "password_error"),
        AUTH_ERROR(1402, "auth_error"),
        RESOURCE_PERMISSION_DENIED(1403, "resource_permission_denied"),
        PERMISSION_VERIFY_USER_NOT_EXISTS(1404, "permission_verify_user_no_exists"),
        PERMISSION_VERIFY_PROJECT_NOT_EXISTS(1405, "permission_verify_project_no_exists"),

        //group相关的以 15开头
        GROUP_NOT_EXIST(1500, "group_not_exist"),
        GROUP_USER_EXISTS(1501, "group_user_exists"),
        GROUP_MEMBER_NOT_EXIST(1502, "group_member_not_exist"),
        GROUP_TOPIC_TITLE_NOT_EMPTY(1503, "group_topic_title_not_empty"),
        GROUP_TOPIC_CONTENT_NOT_EMPTY(1504, "group_topic_CONTENT_not_empty"),

        //task相关的以 16 开头
        TASK_NOT_EXIST(1600, "task_not_exist"),
        TASK_FIELDS_NOT_ALL_NULL(1601, "task_fields_not_all_null"),
        TASK_WATCHER_CANCEL_FAIL(1602, "task_watcher_cancel_fail"),
        TASK_STATUS_DUPLICATED(1603, "task_status_duplicated"),
        TASK_ALREADY_WATCHED(1604, "task_already_watched"),
        TASK_START_DATE_ERROR(1607, "task_start_date_error"),
        TASK_END_DATE_ERROR(1608, "task_end_date_error"),

        // tweet相关的以 17 开头
        TWEET_COMMENT_CONTENT_NOT_EMPTY(1700, "tweet_comment_content_not_empty"),
        TWEET_REPEAT(1701, "tweet_repeat"),
        TWEET_FAST(1702, "tweet_fast"),
        TWEET_COMMENT_REPEAT(1703, "tweet_comment_repeat"),
        TWEET_LIKE_REPEAT(1704, "tweet_like_repeat"),
        TWEET_IMAGE_BIG_THAN_5M(1705, "tweet_image_big_than_5m"),
        TWEET_IMAGE_INSERT_ERROR(1706, "tweet_image_insert_error"),
        TWEET_IMAGE_LIMIT_N(1707, "tweet_image_limit_n"),
        TWEET_NOT_OWNER(1708, "tweet_not_owner"),
        TWEET_NOT_EXISTS(1709, "tweet_not_exists"),
        TWEET_ALREADY_RECOMMEND(1710, "tweet_already_recommend"),

        // project_tweet相关的以175开头
        PROJECT_TWEET_NOT_EXISTS(1751, "project_tweet_not_exists"),
        PROJECT_TWEET_REPEAT(1752, "project_tweet_repeat"),
        PROJECT_TWEET_FAST(1753, "project_tweet_fast"),

        //QC相关以18开头
        QC_CREATE_TASK_FAILED(1800, "qc_create_task_failed"),
        QC_TODAY_TASK_OVER_3(1803, "qc_today_task_over_3"),
        QC_TOTAL_TASK_OVER_20(1804, "qc_total_task_over_20"),
        QC_TASK_PROJECT_ANALYSIS_TASK_MANY(1807, "qc_task_project_analysis_task_many"),
        QC_TASK_USER_ANALYSIS_TASK_MANY(1808, "qc_task_user_analysis_task_many"),
        QC_REQUEST_TIMEOUT(1805, "qc_request_timeout"),
        QC_ENGINE_DATA_ERROR(1806, "qc_engine_data_error"),
        QC_INTERNAL_ERROR(1807, "qc_internal_error"),
        QC_TODAY_TASK_OVER_LIMIT(1808, "qc_today_task_over_limit"),
        QC_HAS_ALIKE_TASK(1809, "qc_has_alike_task"),


        //Image以19开头
        IMAGE_DOWNLOAD_ERROR(1900, "image_download_error"),

        //ProjectGroup以20开头
        PROJECT_GROUP_SAME_NAME(2000, "project_group_same_name"),
        PROJECT_ALREADY_IN_GROUP(2001, "project_already_in_group"),
        PROJECT_GROUP_PROJECT_LIMIT(2002, "project_group_project_limit"),
        PROJECT_GROUP_NAME_TOO_LONG(2003, "project_group_name_too_long"),

        //CodeInsight 以 21 开头
        CODE_INSIGHT_TASK_EXIST(2100, "code_insight_task_exist"),
        CODE_INSIGHT_NOT_EXIST(2101, "code_insight_not_exist"),
        CODE_INSIGHT_TASK_FAILED(2102, "code_insight_task_failed"),
        CODE_INSIGHT_NOT_DONE(2103, "code_insight_not_done"),

        // invite
        INVITE_EMAIL_IS_EXISTS(2201, "invite_email_is_exists"),
        INVITE_REPEAT(2202, "invite_repeat"),
        INVITE_CODE_ERROR(2203, "invite_code_error"),
        INVITE_FRIEND_NEED_CAPTCHA(2204, "invite_friend_need_captcha"),
        INVITE_RATE_HIGH(2206, "invite_rate_too_high"),

        // project pin
        PROJECT_PIN_LIMIT(2300, "project_pin_limit"),

        //
        LABEL_NAME_ERROR(2500, "label_name_error"),
        LABEL_EXIST(2501, "label_exist"),
        LABEL_COLOR_ERROR(2502, "label_color_error"),

        // admin
        ADMIN_APP_UPDATE_VERSION_NOT_EMPTY(2600, "admin_app_update_version_not_empty"),
        ADMIN_APP_UPDATE_VERSION_FORMAT_ERROR(2601, "admin_app_update_version_format_error"),
        ADMIN_APP_UPDATE_BUILD_NOT_EMPTY(2602, "admin_app_update_build_not_empty"),
        ADMIN_APP_UPDATE_MESSAGE_NOT_EMPTY(2603, "admin_app_update_message_not_empty"),
        ADMIN_API_PERMISSION_CLOSED(2606, "admin_api_permission_closed"),
        ADMIN_PERMISSION_DENY(2606, "admin_permission_deny"),
        ADMIN_USER_BEEN_DISABLED(2607, "admin_user_been_disabled"),

        ADMIN_IMAGE_TOO_LARGE(2608, "admin_image_too_large"),
        ADMIN_IMAGE_ERROR(2609, "admin_image_error"),
        ADMIN_IMAGE_TOO_BIG(2610, "admin_image_too_big"),
        ADMIN_IMAGE_TOO_SMALL(2611, "admin_image_too_small"),
        ADMIN_IMAGE_ERROR_FILE(2612, "admin_image_error_file"),

        ADMIN_MUST_OPEN_2FA(2613, "admin_must_open_2fa"),


        ADMIN_TOPIC_IMAGE_PIXEL_INVALID(2613, "admin_topic_image_pixel_invalid"),
        ADMIN_BULK_MESSAGE_TESTING_INVALID(2614, "admin_bulk_message_testing_invalid"),
        ADMIN_INVITE_CODE_REST_NOT_ENOUGH(2615, "admin_invite_code_rest_not_enough"),
        ADMIN_QUICK_SELECTION_NO_RESULT(2616, "admin_quick_selection_no_result"),

        // payment
        PAYMENT_CHARGELOG_ALREADY_CLOSE(2700, "payment_charge_log_already_close"),
        PAYMENT_CHARGELOG_ALREADY_SUCCESS(2701, "payment_charge_log_already_success"),
        PAYMENT_CHARGELOG_NOT_PROGRESS(2702, "payment_charge_log_not_progress"),
        PAYMENT_NOT_SUFFICIENT_FUNDS(2703, "payment_not_sufficient_funds"),
        PROJECT_PAYMENT_NOT_EXPIRE(2704, "project_payment_not_expire"),
        PROJECT_PAYMENT_UPGRADE_CYCLE_ERROR(2705, "project_payment_upgrade_cycle_error"),
        PROJECT_ENTERPRISE_UPGRADE_ERROR(2706, "project_enterprise_upgrade_error"),
        PROJECT_ENTERPRISE_TRANSFER_ERROR(2707, "project_enterprise_transfer_error"),
        PROJECT_ENTERPRISE_UPGRADE_PLAN_ERROR(2708, "project_enterprise_upgrade_plan_error"),
        PROJECT_ENTERPRISE_PROJECT_PLAN_SET_ERROR(2708, "project_enterprise_project_plan_set_error"),
        PROJECT_PAYMENT_RENEW_CYCLE_LIMIT_2YEAR(2709, "project_payment_renew_cycle_limit_2year"),
        PROJECT_PAYMENT_NO_RENEWABLE_PROJECT(2709, "project_payment_no_renewable_project"),


        // voucher
        VOUCHER_NOT_EXITS(2750, "voucher_not_exits"),
        VOUCHER_IS_OVERDUE(2751, "voucher_is_overdue"),
        VOUCHER_IS_USED(2752, "voucher_is_used"),
        VOUCHER_MONEY_MUST_BE_LESS_THEN_PRICE(2753, "voucher_money_must_be_less_then_price"),

        // invoice
        INVOICE_PRICE_LIMIT(2800, "invoice_price_limit"),
        INVOICE_ALREADY_HANDLE(2801, "invoice_already_handle"),
        INVOICE_ALREADY_DELIVERY(2802, "invoice_already_deliver"),

        //oauth
        OAUTH_CLIENT_ID_ERROR(3000, "error_client_id"),
        OAUTH_URL_MISMATCH(3001, "oauth_url_mismatch"),
        OAUTH_CLIENT_ID_KEY_MISMATCH(3002, "oauth_client_id_key_mismatch"),
        OAUTH_VALIDATE_CODE_ERROR(3003, "oauth_validate_code_error"),
        OAUTH_AUTH_EXPIRED(3004, "oauth_auth_expired"),
        OAUTH_SCOPE_ERROR(3005, "oauth_scope_error"),
        OAUTH_REFRESH_TOKEN_ERROR(3006, "oauth_refresh_token_error"),
        OAUTH_ACCESS_TOKEN_IS_VALID(3007, "oauth_access_token_is_valid"),
        OAUTH_GRANT_TYPE_ERROR(3008, "oauth_grant_type_error"),
        CUSTOMER_ICON_TOO_LARGE(3009, "customer_icon_too_large"),
        CUSTOMER_ICON_ERROR(3010, "customer_icon_error"),
        CUSTOMER_ICON_TOO_BIG(3011, "customer_icon_too_big"),
        CUSTOMER_ICON_TOO_SMALL(3012, "customer_icon_too_small"),
        CUSTOMER_ICON_ERROR_FILE(3013, "customer_icon_error_file"),
        OAUTH_USER_ERROR(3014, "oauth_user_error"),
        OAUTH_USER_SCOPE_ERROR(3015, "oauth_user_scope_error"),
        OAUTH_ACCESS_TOKEN_INVALID(3016, "oauth_access_token_invalid"),
        OAUTH_AUTHORIZE_EXPIRED(3017, "oauth_authorize_invalid"),
        OAUTH_PLATFORM_NOT_SUPPORT(3018, "oauth_platform_not_support"),
        OAUTH_TEAM_ERROR(3019, "oauth_team_error"),
        INSERT_CUSTOMER_ERROR(1, "insert_customer_error"),
        USER_CUSTOMER_NO_PERMISSON(1, "user_customer_no_permission"),
        CUSTOMER_NOT_EXISTS(1, "customer_not_exists"),
        UPDATE_CUSTOMER_ERROR(1, "update_customer_error"),
        OPERATION_FAILED(1, "operation_failed"),
        OAUTH_NO_AUTHORIZE_RECORD(1, "oauth_no_authorize_record"),
        OAUTH_FAILED_TO_FIND_ACCESS_TOKEN(3020, "oauth_failed_to_find_access_token"),
        OAUTH_FAILED_TO_REFRESH_ACCESS_TOKEN(3021, "oauth_failed_to_refresh_access_token"),
        OAUTH_REFUSE_TO_UNBIND_USED_IN_OCD_WITH_PROJECT_NAME(3022, "oauth_refuse_to_unbind_used_in_ocd_with_project_name"),
        OAUTH_ENTERPRISE_WECHAT_NO_BINDING(3023, "oauth_enterprise_wechat_no_binding"),
        OAUTH_ENTERPRISE_WECHAT_ERROR(3024, "oauth_enterprise_wechat_error"),
        OAUTH_PLATFORM_ACCOUNT_ALREADY_BOUND(3025, "oauth_platform_account_already_bound"),
        OAUTH_REDIRECT_URI_MISSING(3026, "oauth_redirect_uri_missing"),
        // oauth import
        OAUTH_GET_CURRENT_USER_FAILED(3027, "oauth_get_current_user_failed"),
        OAUTH_GET_PROJECT_MEMBERS_FAILED(3028, "oauth_get_project_members_failed"),
        OAUTH_GET_USER_PROJECTS_FAILED(3029, "oauth_get_user_projects_failed"),
        OAUTH_GET_DEPLOY_KEYS_FAILED(3030, "oauth_get_deploy_keys_failed"),
        OAUTH_GET_DEPLOY_KEY_DETAIL_FAILED(3031, "oauth_get_deploy_key_detail_failed"),
        OAUTH_ADD_DEPLOY_KEY_FAILED(3032, "oauth_add_deploy_key_failed"),
        OAUTH_DELETE_DEPLOY_KEY_FAILED(3033, "oauth_delete_deploy_key_failed"),
        OAUTH_FORMAT_INVALID(3034, "oauth_format_invalid"),
        // 个人微信
        OAUTH_WECHAT_GET_USER_INFO_ERROR(3044, "oauth_wechat_get_user_info_error"),
        OAUTH_WECHAT_GET_USER_INFO_OVERDUE(3045, "oauth_wechat_get_user_info_overdue"),
        OAUTH_WECHAT_NO_BOUND_ERROR(3046, "oauth_wechat_no_bound_error"),
        MINI_WECHAT_GET_SESSION_ERROR(3047, "mini_wechat_get_session_error"),
        MINI_WECHAT_DECRYPT_ERROR(3048, "mini_wechat_decrypt_error"),
        AOUTH_ALREADY_EXIST(3049, "aouth_already_exist"),

        // topic
        PROJECT_TOPIC_TITLE_NOT_EMPTY(1104, "project_topic_title_not_empty"),
        PROJECT_TOPIC_CONTENT_NOT_EMPTY(1105, "project_topic_content_not_empty"),
        PROJECT_TOPIC_COMMENT_CONTENT_NOT_EMPTY(1108,
                "project_topic_comment_content_not_empty"),
        PROJECT_TOPIC_TITLE_TOO_LONG(1114, "project_topic_title_too_long"),
        TOPIC_ALREADY_WATCHED(3112, "topic_already_watched"),
        TOPIC_TOO_MANY_WATCHER(3113, "topic_too_many_watcher"),
        PROJECT_TOPIC_NOT_EXIST(3114, "project_topic_not_exist"),
        PROJECT_TOPIC_COMMENT_NOT_EXIST(3115, "project_topic_comment_not_exist"),
        PROJECT_TOPIC_COMMENT_ALREADY_UP_VOTE(3116, "project_topic_comment_already_up_vote"),
        PROJECT_TOPIC_COMMENT_NOT_VOTED(3117, "project_topic_comment_not_voted"),


        // two_factor
        TWO_FACTOR_NOT_ENABLE(3200, "two_factor_not_enable"),
        TWO_FACTOR_ALREADY_CLOSE(3201, "two_factor_already_close"),
        TWO_FACTOR_CODE_ERROR(3202, "two_factor_code_error"),
        TWO_FACTOR_AUTH_CODE_NOT_EMPTY(3205, "two_factor_auth_code_not_empty"),
        TWO_FACTOR_ERROR_LIMIT(3206, "two_factor_error_limit"),
        TWO_FACTOR_AUTH_REQUIRED_LOGIN(3207, "two_factor_auth_required_login"),
        TWO_FACTOR_CODE_NOT_VALID(3208, "two_factor_code_not_valid"),
        TWO_FACTOR_CODE_REQUIRED(3209, "two_factor_code_required"),
        TWO_FACTOR_PASSWORD_REQUIRED(3210, "two_factor_password_required"),
        TWO_FACTOR_TOKEN_EXPIRED(3211, "two_factor_token_expired"),
        TWO_FACTOR_ALREADY_OPEN(3212, "two_factor_already_open"),
        // 私信
        MESSAGE_CONTENT_TOO_LONG(3301, "message_content_too_long"),
        MESSAGE_NEED_CAPTCHA(3302, "message_need_captcha"),
        MESSAGE_VOICE_TYPE_ERROR(3303, "message_voice_type_error"),
        MESSAGE_VOICE_TOO_BIG(3304, "message_voice_too_big"),
        MESSAGE_VOICE_TOO_LONG(3305, "message_voice_too_long"),

        // 密保
        SECRET_QUESTION_ALREADY_SETTING(3401, "secret_question_already_setting"),
        SECRET_QUESTION_NOT_SETTING(3402, "secret_question_not_setting"),

        // 资源共享
        SHARE_NOT_FOUND(3501, "share_not_found"),
        SHARE_CREATE_ERROR(3502, "share_create_error"),
        SHARE_DELETE_ERROR(3503, "share_delete_error"),
        SHARE_CREATE_DUPLICATED(3504, "share_create_duplicated"),

        // 手机验证码
        VERIFYCODE_NOT_VALID(3601, "verifycode_not_valid"),

        // 团队
        TEAM_NOT_EXIST(3700, "team_not_exist"),
        TEAM_GK_ALREADY_EXISTS(3701, "team_gk_already_exists"),
        TEAM_ALREADY_HAVE_MEMBER(3702, "team_already_have_member"),
        TEAM_NOT_HAVE_MEMBER(3703, "team_not_have_member"),
        TEAM_NAME_ALREADY_EXISTS(3704, "team_name_already_exists"),
        TEAM_NO_ACCESS(3705, "team_no_access"),
        TEAM_INVALID_TARGET_OWNER(3706, "team_invalid_target_owner"),
        TEAM_ALREADY_HAVE_PROJECT(3707, "team_already_have_project"),
        TEAM_OWNER_CANT_QUIT(3708, "team_owner_cant_quit"),
        TEAM_ADD_MEMBER_FAILED(3709, "team_add_member_failed"),
        TEAM_MEMBER_NOT_EXISTS(3710, "team_member_not_exists"),
        TEAM_CAN_NOT_REMOVE_SELF(3711, "team_can_not_remove_self"),
        TEAM_ICON_TOO_LARGE(3712, "team_icon_too_large"),
        UPDATE_TEAM_ICON_ERROR(3713, "update_team_icon_error"),
        TEAM_ICON_ERROR(3714, "team_icon_error"),
        TEAM_HAVE_PROJECTS(3715, "team_have_projects"),
        TEAM_PUBLIC_PROJECT_CAN_NOT_IMPORT(3716, "team_public_project_can_not_import"),
        TEAM_UPDATE_PROJECT_PERMISSION_FAILED(3717, "team_update_project_permission_failed"),
        TEAM_NOT_HAVE_PROJECT(3718, "team_not_have_project"),
        TEAM_IMPORT_NAME_DUPLICATED(3719, "team_import_name_duplicated"),
        TEAM_PROJECT_TRANSFER_NAME_DUPLICATED(3720, "team_project_transfer_name_duplicated"),
        TEAM_GK_INUSE(3721, "team_gk_inuse"),
        TEAM_CREATE_ERROR(3722, "team_create_error"),
        TEAM_IS_LOCKED(3723, "team_is_locked"),
        MANAGER_SHOULD_HAVE_2FA_TO_ENABLE_FORCE_TEAM_2FA(3724, "manager_should_have_2fa_to_enable_force_team_2fa"),
        TEAM_MEMBER_NAME_ALREADY_EXISTS(3725, "team_member_name_already_exists"),
        TEAM_LEADER_NOT_EXIST(3726, "team_leader_not_exist"),
        TEAM_IS_ADMIN_LOCKED(3727, "team_is_admin_locked"),
        USER_NAME_USED_BY_GLOBAL_KEY(3728, "user_name_used_by_global_key"),
        TEAM_IDENTITY_VERIFICATION_FAILED(3729, "team_identity_verification_failed"),
        TEAM_IDENTITY_VERIFIED(3730, "team_identity_verified"),
        TEAM_IDENTITY_VERIFICATION_UPDATE_FAILED(3731, "team_identity_verification_update_failed"),
        TEAM_IDENTITY_VERIFICATION_TOO_FREQUENTLY(3732, "team_identity_verification_too_frequently"),
        TEAM_MEMBER_HAND_OVER_RUNNING(3733, "team_member_hand_over_running"),

        // Lint
        LINT_TASK_NOT_FOUND(3800, "lint_task_not_found"),
        LINT_TASK_EXISTED(3801, "lint_task_existed"),
        LINT_TASK_NOT_EXPIRED(3802, "lint_task_not_expired"),
        LINT_SETTING_SAVE_FAILED(3803, "lint_setting_save_failed"),
        LINT_INVALID_SETTING_ITEM(3804, "lint_invalid_setting_item"),
        LINT_INVALID_SETTING_ITEM_VALUE_TYPE(3805, "lint_invalid_setting_item_value_type"),
        LINT_INVALID_SETTING_ITEM_VALUE(3806, "lint_invalid_setting_item_value"),

        // Release
        RELEASE_NOT_FOUND(3900, "release_not_found"),
        RELEASE_TAG_EXISTED(3901, "release_tag_existed"),
        RELEASE_COMPARE_TAG_NOT_FOUND(3902, "release_compare_tag_not_found"),
        RELEASE_TAG_NAME_NOT_ALLOW_DELETE(3903, "release_tag_name_not_allow_delete"),
        RELEASE_COMMIT_SHA_EXISTED(3904, "release_commit_sha_existed"),
        RELEASE_TAG_NAME_EXISTED(3905, "release_tag_name_existed"),
        RELEASE_ATTACHMENT_UPLOAD_FAILED(3906, "release_attachment_upload_failed"),
        RELEASE_ATTACHMENT_NOT_FOUND(3907, "release_attachment_not_found"),
        RELEASE_ATTACHMENT_NAME_DUPLICATED(3908, "release_attachment_name_duplicated"),


        WIKI_NOT_FOUND(4000, "wiki_not_found"),
        WIKI_HISTORY_NOT_FOUND(4001, "wiki_history_not_found"),
        WIKI_DO_NOT_DELETE_LAST_HISTORY(4002, "wiki_do_not_delete_last_history"),
        WIKI_DO_NOT_SET_LAST_VERSION(4003, "wiki_do_not_set_last_history"),
        WIKI_HAS_CHILDREN(4004, "wiki_has_children"),

        // Pages 以 40 开头,错误编号在 pages 项目中定义

        // 任务看板以 41 开头
        TASK_BOARD_CREATE_FAIL(4101, "create_board_fail"),
        TASK_BOARD_LIST_CREATE_FAIL(4102, "create_board_list_fail"),
        TASK_BOARD_LIST_CAN_NOT_DELETE_DEFAULT(4103, "can_not_delete_default_board_list"),
        TASK_BOARD_LIST_CAN_NOT_DELETE_WITH_REFERENCED(4104, "can_not_delete_board_list_with_task_referenced"),
        TASK_BOARD_LIST_CREATE_TASK_FAIL(4105, "create_board_list_task_fail"),
        TASK_BOARD_LIST_NOT_FOUND(4106, "task_board_list_not_found"),

        // 版本规划已 42 开头
        MILESTONE_NOT_EXIST(4201, "milestone_not_exist"),
        MILESTONE_NAME_NOT_EMPTY(4202, "milestone_name_not_empty"),
        MILESTONE_NAME_TO_LONG(4203, "milestone_name_to_long"),
        MILESTONE_STATUS_NOT_EMPTY(4204, "milestone_status_not_empty"),

        // WebHook 相关错误以 47 开头,和个人版保持一致
        HOOK_URL_PATTERN_ERROR(4701, "hook_url_pattern_error"),
        HOOK_URL_RESOLVE_ERROR(4702, "hook_url_resolve_error"),
        HOOK_URL_RESERVED_ERROR(4703, "hook_url_reserved_error"),

        // Enterprise 相关以 50~52 开头
        ENTERPRISE_REGISTER_ERROR(5000, "enterprise_register_error"),
        ENTERPRISE_NOT_EXISTS(5003, "enterprise_not_exists"),
        ENTERPRISE_USER_NOT_ADMIN(5004, "enterprise_user_not_admin"),
        ENTERPRISE_ORDER_NOT_EXISTS(5010, "enterprise_order_not_exists"),
        ENTERPRISE_ORDER_PLATFORM_ERROR(5011, "enterprise_order_platform_error"),
        ENTERPRISE_ORDER_CREATE_ERROR(5014, "enterprise_order_create_error"),
        ENTERPRISE_ORDER_HAS_PENDING_ERROR(5015, "enterprise_order_has_pending_error"),
        ENTERPRISE_ORDER_NOT_PENDING(5016, "enterprise_order_not_pending"),
        ENTERPRISE_ORDER_VIP_TYPE_ERROR(5017, "enterprise_order_vip_type_error"),
        ENTERPRISE_GRADE_INVALID_ERROR(5018, "enterprise_grade_invalid_error"),
        ENTERPRISE_INVOICE_NOT_EXISTS(5101, "enterprise_invoice_not_exists"),
        ENTERPRISE_INVOICE_TYPE_ERROR(5102, "enterprise_invoice_type_error"),
        ENTERPRISE_INVOICE_EXPRESS_ERROR(5103, "enterprise_invoice_express_error"),
        ENTERPRISE_INVOICE_CANCEL_ERROR(5104, "enterprise_invoice_cancel_error"),
        ENTERPRISE_INVOICE_UPDATE_ERROR(5105, "enterprise_invoice_update_error"),
        ENTERPRISE_INVOICE_REFUSE_ERROR(5106, "enterprise_invoice_refuse_error"),
        ENTERPRISE_INVOICE_FINISH_ERROR(5107, "enterprise_invoice_finish_error"),
        ENTERPRISE_INVOICE_PROCESS_ERROR(5108, "enterprise_invoice_process_error"),
        ENTERPRISE_INVOICE_STATUS_ERROR(5109, "enterprise_invoice_status_error"),
        ENTERPRISE_INVOICE_FILE_ERROR(5110, "enterprise_invoice_file_error"),
        ENTERPRISE_INVOICE_FILE_BIGGER_ERROR(5111, "enterprise_invoice_file_bigger_error"),
        ENTERPRISE_USER_BANNED_ERROR(5112, "enterprise_user_banned_error"),
        ENTERPRISE_GET_PRE_REGISTER_INFO_FAILED(5113, "enterprise_get_pre_register_info_failed"),
        ENTERPRISE_REGISTER_PHONE_CODE_NOT_SEND(5114, "enterprise_register_phone_code_not_send"),
        ENTERPRISE_REGISTER_GLOBAL_TOO_FREQUENCY(5115, "enterprise_register_global_too_frequency"),
        ENTERPRISE_ORDER_UPDATE_ERROR(5116, "enterprise_order_update_error"),
        ENTERPRISE_ORDER_HAS_BEEN_PAID_SUCCESSFULLY(5117, "enterprise_order_has_been_paid_successfully"),
        ENTERPRISE_ORDER_HAS_BEEN_CLOSE(5118, "enterprise_order_has_been_close"),
        ENTERPRISE_INVOICE_ADDRESS_TOO_LONG(5119, "enterprise_invoice_address_too_long"),
        ENTERPRISE_INVOICE_INFO_TOO_LONG(5120, "enterprise_invoice_info_too_long"),

        ENTERPRISE_INVITE_USER_NOT_EXISTS(5200, "enterprise_invite_user_not_exists"),
        ENTERPRISE_TEAM_NOT_EXISTS(5201, "enterprise_team_not_exists"),
        ENTERPRISE_GLOBAL_KEY_ERROR(5203, "enterprise_global_key_error"),
        USER_GLOBAL_KEY_ERROR(5204, "user_global_key_error"),
        ENTERPRISE_ORDER_PRICE_LOW_ERROR(5205, "enterprise_order_price_low_error"),
        ENTERPRISE_ORDER_PRICE_ERROR(5206, "enterprise_order_price_error"),
        ENTERPRISE_ORDER_ERROR_PACKAGE_MEMBER_COUNT(5207, "enterprise_order_error_package_member_count"),
        ENTERPRISE_PHONE_ERROR(5208, "enterprise_phone_error"),
        ENTERPRISE_INVITE_TOO_MUCH_USER(5209, "enterprise_invite_too_much_user"),
        ENTERPRISE_GLOBAL_KEY_SAME_AS_USER_GLOBAL_KEY(5210, "enterprise_global_key_same_as_user_global_key"),
        ENTERPRISE_ORDER_FREE_PRICE_ERROR(5211, "enterprise_order_free_price_error"),
        ENTERPRISE_ORDER_PRICE_MINIMAL_ERROR(5212, "enterprise_order_price_minimal_error"),
        ENTERPRISE_DOMAIN_ALREADY_TAKEN(5213, "enterprise_domain_already_taken"),
        ENTERPRISE_GLOBAL_KEY_COUNT_ERROR(5214, "enterprise_global_key_count_error"),
        ENTERPRISE_DEDUCTION_NOT_ENOUGHT_ERROR(5215, "enterprise_deduction_not_enough_error"),

        ENTERPRISE_INVITE_USER_SURPLUS(5216, "enterprise_invite_user_surplus"),
        ENTERPRISE_INVITE_USER_EXISTS(5217, "enterprise_invite_user_exists"),
        ENTERPRISE_NORMAL_BALANCE_OVERDUE(5218, "enterprise_normal_balance_overdue"),
        ENTERPRISE_NORMAL_FREE_MEMBER_COUNT(5219, "enterprise_normal_free_member_count"),
        ENTERPRISE_NORMAL_EMAIL_BALANCE(5220, "enterprise_normal_email_balance"),

        ENTERPRISE_INVITE_LINK_MAIL_ERROR(5222, "enterprise_invite_link_mail_error"),
        ENTERPRISE_INVITE_LINK_NOT_EXISTS(5223, "enterprise_invite_link_not_exists"),
        ENTERPRISE_INVITE_LINK_COUNT_MAX(5224, "enterprise_invite_link_count_max"),

        ENTERPRISE_REPEAT_INVITE_USER_INTERVAL(5225, "enterprise_repeat_invite_user_interval"),

        ENTERPRISE_VIP_SCHEDULER_ERROR(5231, "enterprise_vip_scheduler_error"),
        ENTERPRISE_RESTART_SCHEDULER_DATE_ERROR(5232, "enterprise_restart_scheduler_date_error"),
        ENTERPRISE_RESTART_SCHEDULER_DATE_ADVANCED(5233, "enterprise_restart_scheduler_date_advanced"),
        ENTERPRISE_DOMAIN_ORIGINAL_BLANK(5234, "enterprise_domain_original_blank"),
        ENTERPRISE_DOMAIN_PRESENT_BLANK(5235, "enterprise_domain_present_blank"),
        ENTERPRISE_DOMAIN_ORIGINAL_INVALID(5236, "enterprise_domain_original_invalid"),
        ENTERPRISE_DOMAIN_CHANGE_ERROR(5237, "enterprise_domain_change_error"),
        ENTERPRISE_DOMAIN_CANNOT_FREE(5238, "enterprise_domain_cannot_free"),
        ENTERPRISE_DOMAIN_NO_NEED_FREE(5239, "enterprise_domain_no_need_free"),
        ENTERPRISE_DOMAIN_FREE_ERROR(5240, "enterprise_domain_free_error"),
        ENTERPRISE_DOMAIN_CHANGE_NOT_ALLOW(5241, "enterprise_domain_change_not_allow"),

        ENTERPRISE_MEMBER_SHOULD_INVITE(5234, "enterprise_member_should_invite"),
        ENTERPRISE_INVITE_MEMBER_COUNT_OUT_OF_BOUND(5235, "enterprise_invite_member_out_of_bound"),
        ENTERPRISE_INVITE_MEMBER_COUNT_OUT_OF_BOUND_ADMIN(5236, "enterprise_invite_member_out_of_bound_admin"),
        ENTERPRISE_TYPE_PAY_ONLY(5237, "enterprise_type_pay_only"),
        // DGit 相关以 53 开头
        DGIT_REPLICA_NOT_EXISTS(5301, "dgit_replica_not_exists"),
        DGIT_REPLICA_ALREADY_NORMAL(5302, "dgit_replica_already_normal"),
        DGIT_NOT_ENABLED(5303, "dgit_not_enabled"),
        DGIT_NO_NORMAL_REPLICA(5304, "dgit_not_normal_replica"),

        // 统计 54 开头
        STATISTIC_INIT_NO_PROJECT(5401, "statistic_init_no_project"),
        STATISTIC_REFRESH_DENY(5402, "statistic_refresh_deny"),
        STATISTIC_REFRESH_IN_QUEUE(5403, "statistic_refresh_in_queue"),
        STATISTIC_REFRESH_PROGRESS(5404, "statistic_refresh_progress"),
        STATISTIC_REFRESH_FINISHED_IN_FIVE_MINUTE(5405, "statistic_refresh_finished_in_five_minute"),
        STATISTIC_REFRESH_FINISHED_IN_HALF_HOUR(5406, "statistic_refresh_finished_in_half_hour"),

        // 提醒以 55 开头
        REMIND_TARGET_NOT_FOUND(5501, "remind_target_not_found"),
        REMIND_NOT_FOUND(5502, "remind_not_found"),
        REMIND_LIMITED(5503, "remind_limited"),

        // Demo
        DEMO_TEMPLATE_NOT_FOUND(5601, "demo_template_not_found"),
        FILE_COPY_EXCEPTION(5602, "file_copy_exception"),
        DEMO_NOT_FOUND(5603, "demo_not_found"),

        // aritfact error start with 61
        ARTIFACT_REQUEST_RESOURCE_FAILED(6101, "artifact_request_resource_failed"),
        ARTIFACT_CONVERT_TO_DTO_FAILED(6102, "artifact_convert_to_dto_failed"),
        ARTIFACT_SCANNING_CREDENTIAL_TYPE_ERROR(6103, "artifact_scanning_credential_type_error"),

        // cd
        CD_HOST_NOT_FOUND(6201, "cd_host_not_found"),
        CD_TASK_NOT_FOUND(6204, "cd_task_not_found"),
        CD_TASK_HOST_INVALID(6205, "cd_task_host_invalid"),
        CD_CREDENTIAL_TYPE_NOT_EXIST(6206, "cd_credential_type_not_exist"),
        CD_CURRENT_USER_NOT_IS_TENCENT_CLOUD_USER(6207, "cd_current_user_not_is_tencent_cloud_user"),
        CD_USER_HAS_NO_PERMISSION_TKE(6208, "cd_user_has_no_permission_tke"),
        CD_PRIMARY_USER_HAS_NO_PERMISSION_TKE(6209, "cd_primary_user_has_no_permission_tke"),
        CD_PRIMARY_USER_HAS_NO_PERMISSION_CODING(6210, "cd_primary_user_has_no_permission_coding"),
        CD_PRIMARY_USER_HAS_NO_UNAUTHORIZED_OPERATION(6211, "cd_primary_user_has_no_unauthorized_operation"),
        CD_GET_TEMP_CRED_FAILURE(6212, "cd_get_temp_cred_failure"),
        CD_CREATE_K8S_CRED_FAILURE(6212, "cd_create_k8s_cred_failure"),
        CD_CREATE_SERVER_ACCOUNT_FAILURE(6213, "cd_create_server_account_failure"),

        // ci
        CI_JOB_NOT_FOUND(6301, "ci_job_not_found"),
        CI_BUILD_NOT_FOUND(6302, "ci_build_not_found"),
        CI_JENKINS_FILE_NOT_FOUND(6303, "ci_jenkins_file_not_found"),
        CI_ALREADY_OPENED(6304, "ci_already_opened"),
        CI_BUILD_ALREADY_ENDED(6305, "ci_build_already_ended"),
        CI_JOB_ENV_NAME_TOO_LONG(6306, "ci_job_env_name_too_long"),
        CI_JOB_ENV_VALUE_TOO_LONG(6307, "ci_job_env_value_too_long"),
        CI_SHOULD_NOT_TRIGGER(6308, "ci_should_not_trigger"),
        CI_BUILD_SYSTEM_BUSY(6310, "ci_build_system_busy"),
        CI_JOB_NUMBER_MAX(6311, "ci_job_number_max"),
        CI_JOB_NAME_LENGTH_MAX(6312, "ci_job_name_length_max"),
        CI_JOB_NAME_ERROR(6313, "ci_job_name_error"),
        CI_JOB_NAME_ALREADY_EXIST(6314, "ci_job_name_already_exist"),
        CI_JENKINS_FILE_PATH_ALREADY_EXIST(6315, "ci_jenkins_file_path_already_exist"),
        CI_JENKINS_FILE_PATH_ERROR(6316, "ci_jenkins_file_path_error"),
        CI_JENKINS_FILE_PATH_PATTERN_ERROR(6317, "ci_jenkins_file_path_pattern_error"),
        CI_TRIGGER_BUILD_ERROR(6318, "ci_trigger_build_error"),

        NOT_IN_WHITE_LIST(6401, "not_in_white_list"),

        ABSOLUTE_PATH_ERROR(6501, "absolute_path_error"),
        CI_STEP_APPROVAL_PERMISSION_NOT_ALLOW(6502, "ci_step_approval_permission_not_allow"),
        CI_STEP_APPROVAL_HAVE_DONE(6503, "ci_step_approval_have_done"),

        CI_CREDENTIAL_INSERT_ERROR(6601, "ci_credential_insert_error"),
        CI_CREDENTIAL_NOT_EXIST(6602, "ci_credential_not_exist"),
        CI_CREDENTIAL_DELETE_ERROR(6603, "ci_credential_delete_error"),
        CI_CREDENTIAL_UPDATE_ERROR(6604, "ci_credential_update_error"),
        CI_CREDENTIAL_TYPE_INVALID(6605, "ci_credential_type_invalid"),
        CI_CREDENTIAL_TASK_TYPE_INVALID(6606, "ci_credential_task_type_invalid"),

        // ci certificate
        CI_CERTIFICATE_CREATE_ERROR(6701, "ci_certificate_create_error"),
        CI_CERTIFICATE_NOT_EXIST_OR_ACCESS_DENY(6702, "ci_certificate_not_exist_or_access_deny"),
        CI_CERTIFICATE_UPDATE_ERROR(6703, "ci_certificate_update_error"),
        CI_CERTIFICATE_DELETE_ERROR(6704, "ci_certificate_delete_error"),
        CI_CERTIFICATE_FILE_EMPTY(6708, "ci_certificate_file_empty"),
        CI_CERTIFICATE_FILE_WRITE_ERROR(6709, "ci_certificate_file_write_error"),

        ROLE_ALREADY_EXISTS(8001, "role_name_already_exists"),
        ROLE_NOT_ALLOW_EDIT(8002, "role_not_allow_edit"),
        ROLE_NOT_ALLOW_DELETE(8003, "role_not_allow_delete"),
        ROLE_PERMISSION_NOT_ALLOW_EDIT(8004, "role_permission_not_allow_edit"),
        ROLE_NOT_ALLOW_ASSIGN(8005, "role_not_allow_assign"),

        // edit 71开头
        PROTECT_BRANCH_PERMISSION_DENIED(7101, "project_branch_permission_denied"),

        //setting
        SETTING_EXIST(7201, "setting_key_exist"),

        // deploy token 72开头
        DEPLOY_TOKEN_NOT_EXIST(7202, "deploy_token_not_exist"),
        DEPLOY_TOKEN_PROJECT_NOT_MATCH(7203, "deploy_token_project_not_match"),
        DEPLOY_TOKEN_CREATOR_NOT_MATCH(7204, "deploy_token_creator_not_match"),
        DEPLOY_TOKEN_PASSWORD_EMPTY(7205, "deploy_token_password_empty"),
        DEPLOY_TOKEN_STATUS_EMPTY(7206, "deploy_token_status_empty"),
        DEPLOY_TOKEN_SCOPE_EMPTY(7207, "deploy_token_scope_empty"),
        DEPLOY_TOKEN_SCOPE_INVALID(7208, "deploy_token_scope_invalid"),
        DEPLOY_TOKEN_NAME_TOO_LONG(7209, "deploy_token_name_too_long"),
        DEPLOY_TOKEN_USER_SCOPE_INVALID(7210, "deploy_token_user_scope_invalid"),
        DEPLOY_TOKEN_NAME_EMPTY(7299, "deploy_token_name_empty"),
        DEPLOY_TOKEN_DISABLED(7300, "deploy_token_disabled"),
        DEPLOY_TOKEN_EXPIRED(7301, "deploy_token_expired"),

        // dev 73 开头
        DEV_YUNAPI_NOT_ALLOWED(7301, "dev_yunapi_not_allowed"),
        DEV_YOUR_MAINACCOUNT_NOT_REGISTER(7302, "dev_your_mainaccount_not_register"),
        DEV_QCLOUD_USER_CHANGE(7303, "dev_qcloud_user_change"),
        DEV_QCLOUD_USER_ERROR(7304, "dev_qcloud_user_error"),
        DEV_YUNAPI_ERROR(7305, "dev_yunapi_error"),
        DEV_MAIN_ACCOUNT_NOT_REGISTER(7306, "dev_main_account_not_register"),
        DEV_SUB_ACCOUNT_NOT_REGISTER(7307, "dev_sub_account_not_register"),
        DEV_NOT_IN_WHITE_LIST(7308, "dev_not_in_white_list"),
        DEV_INNER_PARAMS_ERROR(7309, "dev_inner_params_error"),
        DEV_INNER_SERVICE_ERROR(7310, "dev_inner_service_error"),
        CHANGE_DEFAULT_PASSWORD(7311, "change_default_password"),
        DEV_YUNAPI_PROTOCOL_ERROR(7312, "dev_yunapi_protocol_error"),
        DEV_INNER_ACCESS_ERROR(7313, "dev_inner_access_error"),
        DEV_NOT_MAIN_ACCOUNT(7314, "dev_not_main_account"),

        // ISSUE 相关 8 开头，包括缺陷、需求、自定义工作流等
        ISSUE_PROJECT_ISSUE_NOT_CREATED(8000, "issue_project_issue_not_created"),   // 项目问题管理尚未初始化
        ISSUE_PROJECT_ISSUE_INIT_FAILED(8001, "issue_project_issue_init_failed"),   // 项目问题配置初始化失败
        ISSUE_NOT_EXIST(8002, "issue_not_exist"),       // 问题不存在
        ISSUE_ALREADY_WATCHED(8003, "issue_already_watched"),       // 问题已关注
        ISSUE_NOT_YET_WATCHED(8004, "issue_not_yet_watched"),       // 问题尚未关注
        ISSUE_PROJECT_SCHEME_NOT_EXIST(8005, "issue_project_scheme_not_exist"),  // 项目问题表单不存在
        ISSUE_PARENT_COMMENT_NOT_EXIST(8006, "issue_parent_comment_not_exist"),       // 父级评论不存在
        ISSUE_COMMENT_IS_NOT_PARENT(8007, "issue_comment_is_not_parent"),       // 评论层级不正确
        ISSUE_COMMENT_NOT_EXIST(8008, "issue_comment_not_exist"),    // 评论不存在
        ISSUE_SCHEME_TYPE_ERROR(8009, "issue_scheme_type_error"),   // 问题表单类型错误
        ISSUE_SCHEME_ISSUE_TYPE_ERROR(8010, "issue_scheme_issue_type_error"),   // 问题表单问题类型错误
        ISSUE_TITLE_TOO_LONG(8011, "issue_title_too_long"),   // 问题标题过长
        ISSUE_TITLE_NOT_EMPTY(8012, "issue_title_not_empty"),  // 问题标题不能为空
        ISSUE_DESCRIPTION_TOO_LONG(8013, "issue_description_too_long"),   // 问题描述过长
        ISSUE_DESCRIPTION_NOT_EMPTY(8014, "issue_description_not_empty"),   // 问题描述不能为空
        ISSUE_STATUS_NOT_EXIST(8015, "issue_status_not_exist"),//问题状态不存在
        ISSUE_ASSIGNER_NOT_EXIST(8016, "issue_assignee_not_exist"),//问题分配人不存在
        ISSUE_ASSIGNER_NO_ACCESS(8017, "issue_assignee_no_access"),//问题分配人没有权限
        ISSUE_FIELD_NO_EXIST(8018, "issue_field_not_exist"),//问题字段不存在
        ISSUE_DEFECT_TYPE_NO_EXIST(8019, "issue_defect_type_not_exist"),//缺陷类型不存在
        ISSUE_PRIORITY_INVALID(8020, "issue_priority_invalid"),//问题优先级不合法
        ISSUE_PROJECT_MODULE_NOT_EXIST(8021, "issue_project_module_not_exist"),//问题模块不存在
        ISSUE_PROJECT_LABEL_NOT_EXIST(8022, "issue_project_label_not_exist"),//问题label不存在
        ISSUE_PROJECT_WATCHER_NOT_EXIST(8023, "issue_project_watcher_not_exist"),//问题关注人不存在
        ISSUE_PROJECT_FILE_NOT_EXIST(8024, "issue_project_file_not_exist"),//文件不存在
        ISSUE_PROJECT_CUSTOM_FIELD_ERROR(8025, "issue_project_custom_field_error"),//自定义字段错误
        ISSUE_CUSTOM_FIELD_REQUIRED(8026, "issue_custom_field_required"),//自定义字段必填
        ISSUE_CUSTOM_FIELD_CANNOT_BE_ZERO(8027, "issue_custom_field_cannot_be_zero"),//自定义字段不能为0
        ISSUE_CUSTOM_FIELD_TYPE_ERROR(8028, "issue_custom_field_type_error"),//自定义字段类型错误
        ISSUE_DUE_DATE_ERROR(8029, "issue_due_date_error"),//截止日期错误
        ISSUE_WATCHER_NOT_EXIST(8030, "issue_watcher_not_exist"),//问题的关注人不存在
        ISSUE_PROJECT_FILE_NOT_REQUIRED(8031, "issue_project_file_required"),//问题文件必填
        ISSUE_REFERENCE_RESOURCE_ERROR(8032, "issue_reference_resource_error"),//问题关联资源错误
        ISSUE_ASSIGNER_REQUIRED(8033, "issue_assignee_required"),//问题分配人必填
        ISSUE_DEFECT_TYPE_REQUIRED(8034, "issue_defect_type_required"),//问题缺陷类型必填
        ISSUE_DUE_DATE_REQUIRED(8035, "issue_due_date_required"),//问题截止日期必填
        ISSUE_PROJECT_LABEL_REQUIRED(8036, "issue_project_label_required"),//问题label必填
        ISSUE_PROJECT_MODULE_REQUIRED(8037, "issue_project_module_required"),//问题模块必填
        ISSUE_PRIORITY_REQUIRED(8038, "issue_priority_required"),//问题优先级必填
        ISSUE_PROJECT_WATCHER_REQUIRED(8039, "issue_project_watcher_required"),//问题关注者必填
        ISSUE_ID_REQUIRED(8040, "issue_id_required"),//问题ID必填
        ISSUE_PROJECT_MODULE_NAME_REQUIRE(8041, "issue_project_module_name_required"),//模块名称必填
        ISSUE_PROJECT_MODULE_NAME_DUPLICATE(8042, "issue_project_module_name_duplicate"),//模块名称重复
        ISSUE_PROJECT_DEFECT_TYPE_NAME_REQUIRE(8043, "issue_project_defect_type_name_required"),//缺陷类型名称必填
        ISSUE_PROJECT_DEFECT_TYPE_NAME_DUPLICATE(8044, "issue_project_defect_type_name_duplicate"),//缺陷类型名称重复
        ISSUE_FILTER_NOT_EXIST(8045, "issue_filter_not_exist"),//过滤器不存在
        SYSTEM_ISSUE_FILTER_CANNOT_DELETE(8046, "system_issue_filter_cannot_delete"),//系统过滤器不可删除
        SYSTEM_ISSUE_FILTER_CANNOT_UPDATE(8047, "system_issue_filter_cannot_update"),//系统过滤器不可更新
        ISSUE_FILTER_NAME_NOT_EMPTY(8048, "issue_filter_name_not_empty"),//问题筛选器名称不能为空
        ISSUE_FILTER_CONTENT_NOT_EMPTY(8049, "issue_filter_content_not_empty"),//问题筛选器条件内容不能为空
        ISSUE_PARENT_NOT_EXIST(8050, "issue_parent_not_exist"),//父issue不存在
        ISSUE_PROJECT_REQUIREMENT_TYPE_NOT_EXIST(8051, "issue_project_requirement_type_not_exist"),//需求类型不存在
        ISSUE_WORKING_HOURS_REQUIRED(8052, "issue_working_hours_required"),//预估工时必填
        ISSUE_REQUIREMENT_TYPE_REQUIRED(8053, "issue_requirement_type_required"),//需求类型必填
        ISSUE_PROJECT_REQUIREMENT_TYPE_NAME_REQUIRE(8054, "issue_project_requirement_type_name_required"),//需求类型名称必填
        ISSUE_PROJECT_REQUIREMENT_TYPE_NAME_DUPLICATE(8055, "issue_project_requirement_type_name_duplicate"),//需求类型名称重复
        ISSUE_PROJECT_DEFECT_TYPE_NOT_EXIST(8056, "issue_project_defect_type_not_exist"),//缺陷类型不存在
        ISSUE_PARENT_HAS_PARENT(8057, "issue_parent_has_parent"),//该父issue已经是子issue
        ISSUE_CHILDREN_REQUIREMENT_HAS_CHILDREN(8058, "issue_children_requirement_has_children"),//子需求已经有子需求，禁止再次作为子需求被关联
        ISSUE_CHILDREN_REQUIREMENT_HAS_PARENT(8059, "issue_children_requirement_has_parent"),//子需求已经关联了其他父需求
        ISSUE_REQUIREMENT_CANNOT_ATTACH_SELF(8060, "issue_requirement_cannot_attach_self"),//不能讲自己添加为子需求
        ISSUE_ASSIGNEE_NOT_IN_FIELD(8061, "issue_assignee_not_in_field"),//处理人不在表单字段中
        ISSUE_PRIORITY_NOT_IN_FIELD(8062, "issue_priority_not_in_field"),//优先级不在表单字段中
        ISSUE_DUEDATE_NOT_IN_FIELD(8063, "issue_due_date_not_in_field"),//截止日期不在表单字段中
        ISSUE_MODULE_NOT_IN_FIELD(8064, "issue_module_not_in_field"),//模块不在表单字段中
        ISSUE_REQUIREMENT_TYPE_NOT_IN_FIELD(8065, "issue_requirement_type_not_in_field"),//需求类型不在表单字段中
        ISSUE_DEFECT_CANNOT_BE_CHILD(8066, "issue_defect_cannot_be_child"),//缺陷不能作为子项
        ISSUE_ONLY_REQUIREMENT_CAN_HAVE_CHILD(8067, "issue_only_requirement_can_have_child"),//只有需求可关联子项
        ISSUE_ASSIGNEE_CANNOT_CANCEL_WATCH(8068, "issue_assignee_cannot_cancel_watch"),//问题处理人不能取消关注
        ISSUE_PROJECT_ISSUE_NOT_INIT(8069, "issue_project_issue_not_init"), // 项目问题配置尚未初始化
        ISSUE_FIELD_COMPONENT_TYPE_NOT_EXIST(8070, "issue_field_component_type_not_exist"), // 问题属性值类型不存在
        ISSUE_FIELD_OPTION_ERROR(8071, "issue_field_option_error"), // 问题属性选项错误
        ISSUE_FIELD_UNIT_ERROR(8072, "issue_field_unit_error"), // 问题属性单位错误
        ISSUE_PROJECT_ISSUE_NOT_COPY(8073, "issue_project_issue_not_copy"), // 项目问题配置尚未拷贝完成
        ISSUE_FIELD_OPTION_HAS_USED_ERROR(8074, "issue_field_option_has_used_error"), // 问题属性选项已被使用
        ISSUE_FIELD_NOT_ALLOW_EDIT(8075, "issue_field_not_allow_edit"), // 问题属性不允许编辑
        ISSUE_FIELD_NOT_ALLOW_DELETE(8076, "issue_field_not_allow_delete"), // 问题属性不允许删除
        ISSUE_FIELD_HAS_USED(8077, "issue_field_has_used"), // 问题属性已被使用
        ISSUE_PROJECT_ISSUE_COPY_PERMISSION_INVALID(8078, "issue_project_issue_copy_permission_invalid"),  // 项目问题配置拷贝权限异常
        ISSUE_STATUS_HAS_USED(8079, "issue_status_has_used"),   // 问题状态已被使用
        ISSUE_STATUS_TYPE_NOT_EXIST(8080, "issue_status_type_not_exist"),  // 问题状态类型不存在
        ISSUE_TYPE_UNKNOWN(8081, "issue_type_unknown"),   // 问题类型未知
        ISSUE_FIELD_HAS_CONTENT(8082, "issue_field_has_content"),   // 属性正在使用
        ISSUE_FIELD_DEFAULT_VALUE_ERROR(8083, "issue_field_default_value_error"),  // 属性默认值错误
        SYSTEM_ISSUE_FIELD_NOT_ALLOW_CHANGE(8084, "system_issue_field_not_allow_change"),  // 系统属性不允许修改
        ISSUE_DEFAULT_STATUS_NOT_ALLOW_DELETE(8085, "issue_default_status_not_allow_change"),  // 默认状态不允许删除
        ISSUE_STATUS_CONTAIN_STEP_NOT_ALLOW_DELETE(8086, "issue_status_contain_step_not_allow_change"),  // 状态包含步骤不允许删除
        ISSUE_STATUS_STEP_NAME_TOO_LONG(8087, "issue_status_step_name_too_long"),//状态步骤的名字太长
        ISSUE_START_STATUS_ILLEGAL(8088, "issue_start_status_illegal"),//开始状态不合法
        ISSUE_END_STATUS_ILLEGAL(8089, "issue_end_start_status_illegal"),//结束状态不合法
        ISSUE_STATUS_STEP_ALREADY_EXIST(8090, "issue_status_step_already_exist"),//状态步骤以及存在
        ISSUE_STATUS_STEP_NOT_EXIST(8091, "issue_status_step_not_exist"),//状态步骤不存在
        ISSUE_FIELD_NAME_DUPLICATE_ERROR(8092, "issue_field_name_duplicate"),  // 属性不允许重名
        ISSUE_FIELD_OPTION_DUPLICATE_ERROR(8093, "issue_field_option_duplicate"),  // 属性选项不允许重名
        ISSUE_FIELD_OPTION_AT_LEAST_ONE(8094, "issue_field_option_at_least_one"),  // 属性选项最少为1项
        ISSUE_FIELD_OPTION_NOT_ALLOW_UPDATED(8095, "issue_field_option_not_allow_updated"), // 属性选项不允许更新
        DELETE_MEMBER_FAIL_HAVE_PROGRESS_ISSUE(8096, "delete_member_fail_have_progress_issue"), // 删除项目用户
        QUIT_PROJECT_FAIL_HAVE_PROGRESS_ISSUE(8097, "quit_project_fail_have_progress_issue"),  // 用户退出项目
        ITERATION_COMPLETED_NOT_ALLOW_EDIT(8098, "iteration_completed_not_allow_edit"), // 迭代已经完成不允许修改
        ITERATION_NOT_EXIST(8099, "iteration_not_exist"), // 迭代不存在
        ITERATION_COMPLETED_NOT_ALLOW_JOIN(8100, "iteration_completed_not_allow_join"), // 迭代已经完成不允许添加
        CHILD_ISSUE_NOT_ALLOW_JOIN_ITERATION(8101, "child_issue_not_allow_join_iteration"), // 子问题不允许加入迭代
        FROM_ITERATION_NOT_EXIST(8102, "from_iteration_not_exit"), // 来源迭代不存在
        TARGET_ITERATION_CODE_INVALID(8103, "target_iteration_code_invalid"), //目标迭代 ID 不正确
        ITERATION_TIME_ERROR(8104, "iteration_time_error"),//迭代选择时间错误
        ITERATION_START_TIME_NOT_ALLOW_EDIT(8105, "iteration_start_time_not_allow_edit"),//迭代开始时间不允许编辑
        ITERATION_WATCHER_IS_REQUIRED(8106, "iteration_watcher_is_required"),//迭代关注者必填
        ITERATION_ASSIGNEE_NOT_EXIST(8107, "iteration_assignee_not_exist"),//迭代处理人不存在
        ISSUE_RELATED_TYPE_ERROR(8108, "issue_related_type_error"),//问题关联类型不合法
        ISSUE_FIELD_SET_REQUIRED_ERROR(8109, "issue_field_set_required_error"),//属性必填时必须指定默认值或者在创建页显示
        ITERATION_HAS_COMPLETED_ERROR(8110, "iteration_has_completed_error"),//迭代已经完成
        ITERATION_HAS_STARTED_ERROR(8111, "iteration_has_started_error"),//迭代已经开始
        ISSUE_STATUS_TRANSFER_NOT_EXIST(8112, "issue_status_transfer_not_exist"),//问题步骤不存在
        ISSUE_STATUS_TRANSFER_PRIORITY_TYPE_ERROR(8113, "issue_status_transfer_priority_type_error"),//问题流转权限类型不合法
        ISSUE_STATUS_TRANSFER_PRIORITY_ROLE_NOT_EXIST(8114, "issue_status_transfer_priority_role_not_exist"),//问题流转权限角色不存在
        ISSUE_STATUS_TRANSFER_PRIORITY_USER_TYPE_ERROR(8115, "issue_status_transfer_priority_user_type_error"),//问题流转权限用户类型不合法
        ISSUE_STATUS_UPDATE_PERMISSION_DENY(8116, "issue_status_update_permission_deny"),//没有权限修改改状态
        ISSUE_FIELD_HAS_USED_IN_DEFECT(8117, "issue_field_has_used_in_defect"), // 问题属性在缺陷已被使用【共用一个错误码】
        ISSUE_FIELD_HAS_USED_IN_MISSION(8117, "issue_field_has_used_in_mission"), // 问题属性在工作已被使用【共用一个错误码】
        ISSUE_FIELD_HAS_USED_IN_REQUIREMENT(8117, "issue_field_has_used_in_requirement"), // 问题属性在需求已被使用【共用一个错误码】
        ISSUE_CODES_REQUIRED(8118, "issue_codes_required"), // 事务编码必填
        ISSUE_EPIC_NOT_EXIST(8119, "epic_not_exist"),   //史诗不存在
        ISSUE_STATUS_NAME_EXIST(8120, "issue_status_name_exist"), // 问题状态名不能重复
        AGILE_FUNCTION_CLOSED(8121, "issue_function_closed"), // 敏捷管理功能已关闭
        ISSUE_SUB_TASK_HAVE_NO_PARENT_ISSUE(8122, "issue_sub_task_must_have_parent_issue"),// 子任务必须要有父模块
        ISSUE_SUB_TASK_HAVE_ERROR_PARENT_ISSUE_TYPE(8123, "issue_sub_task_have_error_parent_issue_type"),// 子任务的父模块类型错误
        ISSUE_SUB_TASK_NOT_MOVED(8124, "issue_sub_task_not_moved"),// 子任务没有发生移动
        ISSUE_EPIC_ISSUE_TYPE_NOT_ALLOWED(8125, "issue_epic_issue_type_not_allowed"),   //史诗关联事务类型非法
        ISSUE_EPIC_CANNOT_JOIN_ITERATION(8126, "issue_epic_can_not_join_iteration"),   //史诗不能加入迭代
        ISSUE_SUB_TASK_SORT_EQUALS(8127, "issue_sub_task_sort_equals"), //子任务排序相同
        ISSUE_ITERATION_SORT_ERROR(8128, "issue_iteration_sort_error"), // issue 排序失败
        ISSUE_BACKLOG_DATA_INIT_ERROR(8129, "issue_backlog_data_init_error"), // issue backlog 排序初始化报错
        ISSUE_BACKLOG_DATA_INITING(8130, "issue_backlog_data_initing"), // issue backlog 排序初始化进行中
        ISSUE_SORT_NOT_EXISTS(8131, "issue_sort_not_exists"), // 排序不存在
        AGILE_FEATURE_MODULE_CODE_NOT_ALLOWED(8132, "agile_feature_module_code_not_allowed"), //敏捷模块编码非法
        AGILE_MODULE_NOT_EXISTS(8133, "agile_module_not_exists"), //敏捷模块名不存在
        ISSUE_SMART_CREATE_TYPE_NOT_ALLOWED(8134, "issue_smart_create_type_not_allowed"), //快速创建事项类型不合法
        STORY_POINT_TYPE_NOT_EXIST(8135, "story_point_type_not_exist"),       // 故事点类型不存在
        ISSUE_EPIC_ISSUE_ALREADY_EXISTS(8136, "issue_epic_issue_already_exists"),   //史诗事务关联已存在

        // 看板
        KANBAN_COLUMN_TYPE_CANNOT_EDIT(8201, "kanban_column_type_cannot_edit"),
        KANBAN_COLUMN_NAME_REQUIRED(8202, "kanban_column_name_required"),
        KANBAN_COLUMN_NAME_TOO_LONG(8203, "kanban_column_name_too_long"),
        KANBAN_COLUMNS_AT_LEAST_ONE(8204, "kanban_columns_at_least_one"),
        KANBAN_COLUMN_NOT_EXISTS(8205, "kanban_column_not_exists"),
        KANBAN_COLUMN_STATUS_DUPLICATE(8206, "kanban_column_status_duplicate"),

        // 用户项目配置
        USER_PROJECT_SETTING_CODE_NOT_EXISTS(8301, "user_project_setting_code_not_exists"),

        // project token 认证
        PROJECT_AUTH_NULL(9001, "project_auth_null"),
        PROJECT_AUTH_PROJECT_NOT_EXISTS(9002, "project_auth_project_not_exists"),
        PROJECT_AUTH_SCOPE_INVALID(9003, "project_auth_scope_invalid"),
        PROJECT_AUTH_DENY(9004, "project_auth_deny"),
        PROJECT_AUTH_TOKEN_DISABLED(9005, "project_auth_token_disabled"),
        PROJECT_AUTH_TOKEN_EXPIRED(9006, "project_auth_token_expired"),

        // user event logs
        EVENT_TYPE_NOT_SUPPORTED(11001, "event_type_not_supported"),

        // 制品库
        // repo
        ARTIFACTS_REPO_NOT_EXISTS(11101, "artifacts_repo_not_exists"),
        ARTIFACTS_REPO_ALREADY_EXISTS(11102, "artifacts_repo_already_exists"),
        ARTIFACTS_REPO_NPM_TEAM_UNIQUE(11103, "artifacts_repo_npm_team_unique"),
        ARTIFACTS_REPO_NPM_PROJECT_ONE_REPO(11104, "artifacts_repo_npm_project_one_repo"),
        ARTIFACTS_REPO_DELETE_FAILED(11105, "artifacts_repo_delete_failed"),
        ARTIFACTS_REPO_WIPE_FAILED(11106, "artifacts_repo_wipe_failed"),
        ARTIFACTS_TYPE_NOT_SUPPORTED(11107, "artifacts_type_not_supported"),
        ARTIFACTS_TYPE_PERMISSION_DENIED(11108, "artifacts_type_permission_denied"),
        ARTIFACTS_TYPE_INVALID(11109, "artifacts_type_invalid"),
        ARTIFACTS_TYPE_MISMATCH(11110, "artifacts_type_mismatch"),
        // pkg
        ARTIFACTS_PKG_NOT_EXISTS(11201, "artifacts_pkg_not_exists"),
        ARTIFACTS_PKG_ALREADY_EXISTS(11202, "artifacts_pkg_already_exists"),
        // version
        ARTIFACTS_VERSION_NOT_EXISTS(11301, "artifacts_version_not_exists"),
        ARTIFACTS_VERSION_RELEASED(11302, "artifacts_version_released"),
        // repo proxy source
        ARTIFACTS_REPO_PROXY_SOURCE_ALREADY_EXISTS(11401, "artifacts_repo_proxy_source_already_exists"),
        ARTIFACTS_REPO_PROXY_SOURCE_GET_ERROR(11402, "artifacts_repo_proxy_source_get_error"),
        ARTIFACTS_REPO_PROXY_SOURCE_INVALID(11403, "artifacts_repo_proxy_source_invalid"),
        ARTIFACTS_REPO_PROXY_SOURCE_NOT_EXISTS(11404, "artifacts_repo_proxy_source_not_exists"),
        // version property
        ARTIFACTS_VERSION_PROPERTY_NOT_EXISTS(11601, "artifacts_version_property_not_exists"),
        ARTIFACTS_VERSION_PROPERTY_ALREADY_EXISTS(11602, "artifacts_version_property_already_exists"),
        ARTIFACTS_VERSION_PROPERTY_GET_ERROR(11603, "artifacts_version_property_get_error"),
        ARTIFACTS_VERSION_PROPERTY_NEW_ERROR(11604, "artifacts_version_property_new_error"),
        ARTIFACTS_VERSION_PROPERTY_UPDATE_ERROR(11605, "artifacts_version_property_update_error"),
        ARTIFACTS_VERSION_PROPERTY_DELETE_ERROR(11606, "artifacts_version_property_delete_error"),
        ARTIFACTS_VERSION_PROPERTY_SAVE_ERROR(11607, "artifacts_version_property_save_error"),
        ARTIFACTS_VERSION_PROPERTY_CANNOT_WRITE_IMMUTABLE_NAME(11608, "artifacts_version_property_cannot_write_immutable_name"),
        // metadata
        ARTIFACTS_METADATA_GET_ERROR(11701, "artifacts_metadata_get_error"),
        // artifact common
        ARTIFACTS_INVALID_PARAMETERS(11801, "artifacts_invalid_parameters"),
        // artifact misc
        ARTIFACTS_DOCKER_ARTIFACT_VERSION_NOT_SUPPORT_DELETE(11901, "artifacts_docker_artifact_version_not_support_delete"),
        ARTIFACTS_PYPI_ARTIFACT_VERSION_NOT_SUPPORT_DELETE(11902, "artifacts_pypi_artifact_version_not_support_delete"),


        // OA 版本
        SEND_TOF_EMAIL_ERROR(11501, "send_tof_email_error"),
        SEND_TOF_EMAIL_SIGNATURE_ERROR(11502, "send_tof_email_signature_error"),
        EXTERNAL_DEPOT_ADDRESS_INVALID(11509, "external_depot_address_invalid"),
        EXTERNAL_DEPOT_CREDENTIAL_INVALID(11510, "external_depot_credential_invalid"),
        EXTERNAL_DEPOT_FETCH_INFO_FAILED(11511, "external_depot_fetch_info_failed"),
        EXTERNAL_DEPOT_ALREADY_BOUND(11512, "external_depot_already_bound"),
        EXTERNAL_DEPOT_ALREADY_BOUND_INFO(11513, "external_depot_already_bound_info"),
        EXTERNAL_DEPOT_CREDENTIAL_NOT_EXIST(11512, "external_depot_credential_not_exist"),

        // 腾讯云控制台绑定
        QCLOUD_CONSOLE_USER_TOKEN_INVALID(12100, "qcloud_console_user_token_invalid"),
        QCLOUD_CONSOLE_USER_NOT_FOUND(12101, "qcloud_console_user_not_found"),
        QCLOUD_CONSOLE_USER_NOT_PRIMARY(12102, "qcloud_console_user_not_primary"),
        QCLOUD_CONSOLE_USER_NOT_SUB(12103, "qcloud_console_user_not_sub"),
        QCLOUD_CONSOLE_USER_NOT_GRANTED(12104, "qcloud_console_user_not_granted"),
        QCLOUD_CONSOLE_ROLE_NOT_GRANTED(12104, "qcloud_console_role_not_granted"),
        QCLOUD_CONSOLE_REGISTER_FAILURE(12105, "qcloud_console_register_failure"),
        QCLOUD_CONSOLE_USER_NOT_BOUND(12106, "qcloud_console_user_not_bound"),
        QCLOUD_CONSOLE_USER_HAVE_BOUND(12107, "qcloud_console_user_have_bound"),
        QCLOUD_CONSOLE_REGISTER_USER_EXISTS(12108, "qcloud_console_register_user_exists"),

        DEPOT_NAME_EXISTS(13000, "depot_name_exists"),
        DEFAULT_DEPOT_NOT_ALLOW_DELETE(13001, "default_depot_not_allow_delete"),
        DEPOT_NAME_ERROR(13002, "depot_name_error"),
        DEPOT_CREATION_FAILED(13003, "depot_creation_failed"),
        DEPOT_SETTING_UPDATE_FAILED(13004, "depot_setting_update_failed"),
        DEPOT_SETTING_UNKNOWN_NAME(13005, "depot_setting_unknown_name"),
        DEPOT_SETTING_INVALID_VALUE(13006, "depot_setting_invalid_value"),
        DEPOT_GIT_TAG_NOT_ALLOW_DELETE(13007, "depot_git_not_allow_delete"),
        DEPOT_ARCHIVE_NOT_ALLOW_DOWNLOAD(13008, "depot_archive_not_allow_download"),

        // 优惠券相关
        COUPON_ENTERPRISE_RECORD_NULL(140001, "coupon_enterprise_record_null"),
        COUPON_NULL(140002, "coupon_null"),
        COUPON_IS_USED(14003, "coupon_is_used"),
        COUPON_EXPIRED(14004, "coupon_expired"),
        COUPON_DEPLOY_ACTION_HAS_DELETED(14005, "coupon_deploy_action_has_deleted"),

        //IP
        IP_WHITE_NAME_NULL(16000, "ip_white_name_null"),
        IP_WHITE_NAME_NOT_EXIST(16001, "ip_white_name_not_exist"),
        IP_WHITE_NAME_FORMAT_ERROR(16002, "ip_white_name_format_error"),
        IP_WHITE_NAME_RESERVE(16003, "ip_white_name_reserve"),
        IP_WHITE_NAME_NOT_CONTAINS_LOCAL(16004, "ip_white_name_not_contains_local"),
        IP_WHITE_NAME_DEPOT_IS_SHARED(16005, "ip_white_name_depot_is_shared"),

        // 查询过滤器
        SEARCH_FILTER_NAME_EXISTS(15000, "search_filter_name_exists"),
        SEARCH_FILTER_INSERT_FAILED(15001, "search_filter_insert_failed"),
        FILTER_CODE_VALID_FAILED(15002, "filter_code_valid_failed"),
        SEARCH_FILTER_NOT_EXISTS(15003, "search_filter_not_exists"),
        NOT_ALLOW_OPERATE_OTHERS_SEARCH_FILTERS(15004, "not_allow_operate_others_search_filters"),
        UPDATE_SEARCH_FILTER_CONTENT_FAILED(15005, "update_search_filter_content_failed"),
        DELETE_SEARCH_FILTER_FAILED(15006, "delete_search_filter_failed"),
        RENAME_SEARCH_FILTER_FAILED(15007, "rename_search_filter_failed"),
        SET_DEFAULT_FILTER_FAILED(15008, "set_default_filter_failed"),

        RELEVANT_LOGIN_VERIFICATION_TOO_FREQUENTLY(170001, "relevant_login_verification_too_frequently"),
        RELEVANT_LOGIN_TYPE_INVALID(170002, "relevant_login_type_invalid"),
        RELEVANT_OAUTH_PASSWORD_NEED_INFO(170003, "relevant_oauth_password_need_info"),
        RELEVANT_OAUTH_INFO_NO_NEED_INFO(170004, "relevant_oauth_info_no_need_info"),
        RELEVANT_OAUTH_INFO_NO_NEED_PASSWORD(170005, "relevant_oauth_info_no_need_password");


        private final int errorCode;
        private final String resourceKey;
        private Object[] args;
        // 初始化散列表的时候需要指定初始化容量，否则这里由于存入的容量过大，会导致不停 rehash，性能堪忧.
        // 这里考虑到现有容量以及未来扩容的情况，暂设定为 2^11=2048
        private static Map<Integer, ExceptionType> errorCodeLookupMap = new HashMap<>(1 << 11);
        private static Map<String, ExceptionType> resourceKeyLookupMap = new HashMap<>(1 << 11);

        static {
            for (ExceptionType e : values()) {
                errorCodeLookupMap.put(e.getErrorCode(), e);
                resourceKeyLookupMap.put(e.getResourceKey(), e);
            }
        }

        ExceptionType(int errorCode, String resourceKey) {
            this.errorCode = errorCode;
            this.resourceKey = resourceKey;
        }

        ExceptionType(int errorCode, String resourceKey, Object[] args) {
            this.errorCode = errorCode;
            this.resourceKey = resourceKey;
            this.args = args;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getResourceKey() {
            return resourceKey;
        }

        public static ExceptionType findTypeByErrorCode(int errorCode) {
            return errorCodeLookupMap.get(errorCode);
        }

        public static ExceptionType findByResourceKey(String resourceKey) {
            return resourceKeyLookupMap.get(resourceKey);
        }

        public Object[] getArgs() {
            return args;
        }

        public void setArgs(Object[] args) {
            this.args = args;
        }

        public String[] getCodeAndKey() {
            return new String[]{
                    String.valueOf(this.errorCode),
                    this.resourceKey
            };
        }
    }

    private int code = 1;
    private String key = "";
    private String msg = "";
    private Map<String, String> map = new HashMap<>();
    private Map<String, Object> data = new HashMap<>();

    public CoreException(int code, String key, String msg) {
        this.code = code;
        this.key = key;
        this.msg = msg;
    }

    public CoreException(int code, String key, String msg, Map<String, Object> data) {
        this.code = code;
        this.key = key;
        this.msg = msg;
        this.data = data;
    }

    public CoreException() {
    }

    public CoreException(int code, Map<String, String> map) {
        this.code = code;
        this.map = map;
    }

    public String getMsg() {
        return msg;
    }

    public String getKey() {
        return key;
    }

    public int getCode() {
        return code;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public Map<String, Object> getData() {
        return data;
    }


    @Override
    public String getMessage() {
        return String.format("code: %d, key: %s, msg: %s", code, key, msg);
    }
}
