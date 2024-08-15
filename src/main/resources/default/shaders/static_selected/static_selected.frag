#version 330

out vec4 FragColor;

in vec2 TexCoords;

uniform sampler2D tex;

void main() {
    vec4 texColor = texture(tex, TexCoords);

    vec4 invert = vec4(1.0 - texColor.rgb, texColor.a);

    FragColor = vec4(1.0, 1.0, 1.0, 1.0);
}