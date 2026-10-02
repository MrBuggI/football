# Football

[![Build](https://github.com/MrBuggI/football/actions/workflows/build.yml/badge.svg)](https://github.com/MrBuggI/football/actions/workflows/build.yml)
[![Release](https://img.shields.io/github/v/release/MrBuggI/football)](https://github.com/MrBuggI/football/releases/latest)
![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-62B47A)
![Loader](https://img.shields.io/badge/loader-Fabric-DBD0B4)
[![License: MIT](https://img.shields.io/badge/license-MIT-blue)](LICENSE)

Мод для Minecraft, добавляющий **футбольный мяч**: сущность, которую можно поставить в мире и пинать.

> **English:** a Fabric 1.20.1 mod that adds a kickable soccer ball entity. Kick it with a hit, push it by running into it. The ball is a port of *soccermod* by nico (MIT).

## Возможности

- Предмет **«Мяч»** во вкладке креатива «Инструменты и утилиты».
- Поставленный мяч становится сущностью, которую можно **пинать ударом** (летит по направлению взгляда) и **толкать телом**, пробегая мимо.
- Звук пинка при ударе.
- Оператор (permission level 2) может подобрать мяч обратно в инвентарь пустой рукой.

Мод намеренно сфокусирован только на мяче: без команд, голов и музыки.

## Установка

1. Установите [Fabric Loader](https://fabricmc.net/use/) для Minecraft 1.20.1.
2. Скачайте `.jar` из раздела [Releases](https://github.com/MrBuggI/football/releases/latest).
3. Положите его вместе с [Fabric API](https://modrinth.com/mod/fabric-api) в папку `mods/`.
4. Запустите игру.

## Технические данные

| Параметр         | Значение                      |
|------------------|-------------------------------|
| Версия Minecraft | 1.20.1                        |
| Загрузчик        | Fabric (Fabric Loader 0.18.4) |
| Fabric API       | 0.92.6+1.20.1                 |
| Java             | 17+                           |
| Версия мода      | 1.0.1                         |

## Архитектура

Пакет `io.github.mrbuggi.football`. Миксинов нет, своих сетевых пакетов нет. Код написан в именах Mojang (official mappings), как в оригинальном моде.

| Файл | Роль |
|---|---|
| `FootballMod` | точка входа: звук, сущность, предмет, атрибуты |
| `FootballModClient` | регистрация слоя модели и рендера |
| `entity/custom/SoccerBallEntity` | мяч: толчок телом, удар, подбор оператором |
| `entity/client/*` | модель, рендер и слой модели мяча |
| `entity/animations/SoccerBallEntityAnimation` | анимация вращения |
| `item/SoccerBallItem` | предмет, который ставит мяч перед игроком |
| `sound/ModSounds` | звук удара |

Мяч унаследован от `Animal`, как в оригинальном soccermod: это даёт готовую физику, отбрасывание и синхронизацию. Плата за это - лишнее поведение животного, которое приходится глушить: нулевая скорость, 5000 здоровья, урон от игрока превращён в удар по мячу.

## Сборка из исходников

Нужен JDK 17.

```bash
./gradlew build       # готовый .jar появится в build/libs/
./gradlew runClient   # запустить клиент Minecraft с модом
```

Каждый push проверяется сборкой в GitHub Actions, а при публикации тега `v*` собранный `.jar` автоматически прикладывается к релизу.

## Благодарности

Мяч (код сущности, текстуры и звук пинка) портирован из мода **soccermod** от разработчика nico, используется под лицензией MIT. Портирование на Fabric 1.20.1: **MrBuggI**.

## Лицензия

[MIT](LICENSE). В файле LICENSE отдельно указано происхождение мяча.
