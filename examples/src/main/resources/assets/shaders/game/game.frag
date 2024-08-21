#version 330

out vec4 FragColor;

in vec2 TexCoords;

uniform sampler2D tex;
uniform vec4 color;

void main() {
    FragColor = texture(tex, TexCoords) * color;
}