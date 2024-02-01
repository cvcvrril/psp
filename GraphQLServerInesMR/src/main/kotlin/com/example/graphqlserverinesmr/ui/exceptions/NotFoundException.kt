package com.example.graphqlserverinesmr.ui.exceptions

class NotFoundException() : RuntimeException() {
    override val message: String?
        get() = super.message

}