package dev.vihang.issues.grpc

import dev.vihang.Message
import dev.vihang.MessagePart
import org.junit.Test


class GrpcByteStringUtf8Test {

    @Test
    fun `test gRPC with value 55903 for invalid UTF-8 ByteString`() = assertValidUtf8ByteString(55903)

    @Test
    fun `test gRPC with value 55904 for invalid UTF-8 ByteString`() = assertValidUtf8ByteString(55904)

    private fun assertValidUtf8ByteString(value: Long) {
        assert(
                Message.newBuilder()
                        .addParts(MessagePart.newBuilder()
                                .setUnsignedInt64Field(value))
                        .build()
                        .toByteString()
                        .isValidUtf8) { "$value generates INVALID UTF-8 ByteString" }

        println("$value generates VALID UTF-8 ByteString")
    }
}