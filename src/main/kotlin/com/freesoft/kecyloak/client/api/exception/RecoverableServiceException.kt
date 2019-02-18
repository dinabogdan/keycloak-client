package com.freesoft.kecyloak.client.api.exception

class RecoverableServiceException(override val message: String?,
                                  override val cause: Throwable?) : ServiceException(message, cause)
