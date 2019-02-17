package com.freesoft.kecyloak.client.impl.common

class RecoverableServiceException(override val message: String?,
                                  override val cause: Throwable?) : ServiceException(message, cause)
