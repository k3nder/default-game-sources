#version 330

out vec4 FragColor;

in vec2 TexCoords;

uniform vec4 color;
uniform sampler2D tex;

void main() {
    FragColor = texture(tex, TexCoords) * color;
}