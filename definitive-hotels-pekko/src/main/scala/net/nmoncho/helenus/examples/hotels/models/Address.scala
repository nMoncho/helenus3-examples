package net.nmoncho.helenus.examples.hotels.models

import net.nmoncho.helenus.api.`type`.codec.UDTCodec

final case class Address(
    street: String,
    city: String,
    stateOrProvince: String,
    postalCode: String,
    country: String
) derives UDTCodec
