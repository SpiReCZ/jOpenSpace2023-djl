package cz.jopenspace.djlaidemo.service

import ai.djl.modality.cv.BufferedImageFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource

@SpringBootTest
internal class CaptchaServiceTest {

    private val log = LoggerFactory.getLogger("captcha-breaker-test")

    @Autowired
    private lateinit var captchaService: CaptchaService

    @ParameterizedTest
    @ValueSource(strings = ["ovnh", "pkqt"])
    fun solve(captchaValue: String) {
        log.info("Solving CAPTCHA image for: '${captchaValue}'")
        val captchaImage = with(ClassPathResource("captchas/${captchaValue}.jpg").inputStream) {
            BufferedImageFactory.getInstance().fromInputStream(this)
        }
        val computedValue = captchaService.solve(captchaImage)
        assertThat(computedValue).isEqualTo(captchaValue)
    }
}
