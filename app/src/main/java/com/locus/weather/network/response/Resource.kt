package com.locus.weather.network.response


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
class Resource<T>(
    val status: Status,
    val data: T? = null,
    val exception: ApiServiceException? = null
) {
    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val resource =
            o as Resource<*>
        if (status !== resource.status) {
            return false
        }
        if (if (exception != null) exception != resource.exception else resource.exception != null) {
            return false
        }
        return if (data != null) data == resource.data else resource.data == null
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + (exception?.hashCode() ?: 0)
        result = 31 * result + (data?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Resource{" +
                "status=" + status +
                ", message='" + exception + '\'' +
                ", data=" + data +
                '}'
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(exception: ApiServiceException): Resource<T> {
            return Resource(Status.ERROR, exception = exception)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null)
        }
    }

}