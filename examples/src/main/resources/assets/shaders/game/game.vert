#version 330

layout (location = 0) in vec3 aPos;
layout (location = 1) in vec3 aNormal;
layout (location = 2) in vec2 aTexture;

uniform mat4 model;
uniform mat4 projection;
uniform mat4 view;

out vec3 Normal;
out vec2 TexCoords;
out vec3 FragPos;

void main() {
   gl_Position = projection * view * model * vec4(aPos, 1.0);
    TexCoords = vec2(aTexture.x, 1.0 - aTexture.y);
}