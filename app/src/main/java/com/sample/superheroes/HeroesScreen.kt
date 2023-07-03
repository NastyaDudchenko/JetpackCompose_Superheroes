package com.sample.superheroes

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sample.superheroes.data.HeroesRepository
import com.sample.superheroes.data.model.Hero
import com.sample.superheroes.ui.theme.SuperheroesTheme

@Composable
fun HeroList(
    heroes: List<Hero>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(heroes) {
            HeroItem(
                hero = it,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
fun HeroItem(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.elevation)),
        modifier = modifier.clip(MaterialTheme.shapes.medium)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .sizeIn(minHeight = dimensionResource(id = R.dimen.item_height))
        ) {
            HeroInformation(
                heroName = hero.nameRes,
                heroDesc = hero.descriptionRes,
                modifier = modifier.weight(1F)
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
            HeroIcon(heroIcon = hero.imageRes)
        }
    }
}

@Composable
fun HeroInformation(
    @StringRes heroName: Int,
    @StringRes heroDesc: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = heroName),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(id = heroDesc),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun HeroIcon(
    @DrawableRes heroIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(id = R.dimen.image_size))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.FillWidth,
        alignment = Alignment.TopCenter,
        painter = painterResource(id = heroIcon),
        contentDescription = null
    )
}

@Preview("Light Theme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HeroPreview() {
    val hero = Hero(
        R.string.hero1,
        R.string.description1,
        R.drawable.android_superhero1
    )
    SuperheroesTheme {
        HeroItem(hero = hero)
    }
}

@Preview("Heroes List")
@Composable
fun HeroesPreview() {
    SuperheroesTheme(darkTheme = false) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HeroList(heroes = HeroesRepository.heroes)
        }
    }
}