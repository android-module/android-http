val upload2MyRepo: String? by project

var isUpload2MyRepo = false
if (upload2MyRepo != null) {
    isUpload2MyRepo = upload2MyRepo!!.toBoolean()
}

println("upload-center > $upload2MyRepo")

if (isUpload2MyRepo) {
    apply(from = "../maven-publish.gradle.kts")
} else {
    apply(from = "../bintray-with-maven-publish.gradle.kts")
}