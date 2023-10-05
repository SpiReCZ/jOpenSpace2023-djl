package cz.jopenspace.djlaidemo.props

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("app.model")
class AppModelProps {
    var captchaModel: String = "captcha-model.zip"
}