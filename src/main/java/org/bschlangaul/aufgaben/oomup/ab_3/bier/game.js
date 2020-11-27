// https://bier.drwuro.com/

document['onkeydown'] = keydown;
document['onkeyup'] = keyup;
var canvas = document['getElementById']('canvas');
var ctx = canvas['getContext']('2d');
var character1_up1 = document['getElementById']('character1_up1');
var character1_up2 = document['getElementById']('character1_up2');
var character1_up3 = document['getElementById']('character1_up3');
var character1_down1 = document['getElementById']('character1_down1');
var character1_down2 = document['getElementById']('character1_down2');
var character1_down3 = document['getElementById']('character1_down3');
var character1_dead = document['getElementById']('character1_dead');
var cow_left1 = document['getElementById']('cow_left1');
var cow_left2 = document['getElementById']('cow_left2');
var cow_left3 = document['getElementById']('cow_left3');
var cow_right1 = document['getElementById']('cow_right1');
var cow_right2 = document['getElementById']('cow_right2');
var cow_right3 = document['getElementById']('cow_right3');
var characterSprites = [
    [character1_up1, character1_up2, character1_up1, character1_up3],
    [character1_down1, character1_down2, character1_down1, character1_down3]
];
var beerSprites = [document['getElementById']('beer_r'), document['getElementById']('beer_l')];
var cowSprites = [
    [cow_left1, cow_left2, cow_left3],
    [cow_right1, cow_right2, cow_right3]
];
var tractorSprites = [
    [
        [tractor1_left1, tractor1_left2],
        [tractor1_right1, tractor1_right2]
    ],
    [
        [tractor2_left1, tractor2_left2],
        [tractor2_right1, tractor2_right2]
    ],
    [
        [tractor3_left1, tractor3_left2],
        [tractor3_right1, tractor3_right2]
    ],
    [
        [tractor4_left1, tractor4_left2],
        [tractor4_right1, tractor4_right2]
    ]
];
var imgBackground = document['getElementById']('background');
var arrow_up = document['getElementById']('arrow_up');
var arrow_down = document['getElementById']('arrow_down');
var heart = document['getElementById']('heart');
var restart = document['getElementById']('restart');
var start = document['getElementById']('start');
var cont = document['getElementById']('continue');
var partnerlogo = document['getElementById']('partnerlogo');
var player = document['getElementById']('player');
var SCALE = 0x3;
var FRAME_TIME = 0x19;
var FRAME_RATE = 0x1 / FRAME_TIME;
var STATE_INITIAL = 0x0;
var STATE_GAME = 0x1;
var STATE_NAMEENTRY = 0x2;
var STATE_HIGHSCORES = 0x3;
var STATE_PROMO = 0x4;
var state = STATE_INITIAL;
var tick = 0x0;
var animphase = 0x0;
var px = 0x30;
var py = 0x80;
var ydir = 0x0;
var fdir = 0x0;
var debugtext = 'BIER\x20HOLEN';
var bx = 0x35;
var by = 0x21;
var possessed = ![];
var autobeer = ![];
var cx = [-0x20, 0x80, -0x50, 0xa0, 0x100, -0x80];
var cy = [0x28, 0x36, 0x44, 0x52, 0x60, 0x6e];
var cdir = [0x1, -0x1, 0x1, -0x1, -0x1, 0x1];
var cs = [0x2, 0x4, 0x3, 0x4, 0x3, 0x2];
var NUM_COWS = 0x6;
var score = 0x0;
var deadcnt = 0x0;
var numLives = 0x3;
var gameover = !![];
var hasHighscore = ![];
var waitForHighscore = ![];
var postingHighscore = ![];
var touchblocked = ![];
var restartcnt = 0x0;
var highnames = ['', '', '', '', ''];
var highscores = [0x0, 0x0, 0x0, 0x0, 0x0];
var snowx = [];
var snowy = [];
var NUM_SNOWFLAKES = 0x10;
var id = 0x0;
for (var i = 0x0; i < NUM_SNOWFLAKES; i++) {
    snowx[i] = Math['floor'](Math['random']() * 0x80 + 0x1);
    snowy[i] = Math['floor'](Math['random']() * 0x18 - 0x30);
}
checkHighscore();

function mousedown(_0x8d1dce) {
    ydir = 0x0;
    var _0x9dd093 = canvas['getBoundingClientRect']();
    xoff = _0x9dd093['left'];
    yoff = _0x9dd093['top'];
    arrowypos = getArrowYPos() - 0x41;
    if (_0x8d1dce['clientX'] > 0x0 + xoff && _0x8d1dce['clientX'] < 0x0 + 0x80 + xoff)
        if (_0x8d1dce['clientY'] > arrowypos + yoff && _0x8d1dce['clientY'] < arrowypos + 0x88 + yoff) ydir = -0x1;
    if (_0x8d1dce['clientX'] > 0xe8 + xoff && _0x8d1dce['clientX'] < 0xe8 + 0x80 + xoff)
        if (_0x8d1dce['clientY'] > arrowypos + yoff && _0x8d1dce['clientY'] < arrowypos + 0x88 + yoff) ydir = 0x1;
    if (gameover)
        if (_0x8d1dce['clientX'] > 0x68 + xoff && _0x8d1dce['clientX'] < 0x68 + 0xa0 + xoff)
            if (_0x8d1dce['clientY'] > 0x198 + yoff && _0x8d1dce['clientY'] < 0x198 + 0x30 + yoff) {
                if (!touchblocked) {
                    continueNext();
                    touchblocked = !![];
                }
            } _0x8d1dce['preventDefault']();
}

function mouseup(_0x3a0815) {
    ydir = 0x0;
    _0x3a0815['preventDefault']();
    touchblocked = ![];
}

function touchstart(_0x557759) {
    for (var _0x502321 = 0x0; _0x502321 < _0x557759['changedTouches']['length']; _0x502321++) {
        var _0x504899 = _0x557759['changedTouches'][_0x502321];
        debugtext += _0x504899['clientX'] + '\x20/\x20' + _0x504899['clientY'] + '\x20\x20\x20-\x20\x20\x20';
        mousedown(_0x504899);
    }
    _0x557759['preventDefault']();
}

function touchmove(_0x3f615d) {
    touchstart(_0x3f615d);
}

function touchend(_0x2ae83f) {
    ydir = 0x0;
    _0x2ae83f['preventDefault']();
    touchblocked = ![];
}

function keydown(_0x24dda6) {
    if (_0x24dda6['which'] == 0x26) ydir = -0x1;
    if (_0x24dda6['which'] == 0x28) ydir = 0x1;
    if (_0x24dda6['which'] == 0x20)
        if (gameover) continueNext();
}

function keyup(_0x12805c) {
    if (_0x12805c['which'] == 0x26)
        if (ydir < 0x0) ydir = 0x0;
    if (_0x12805c['which'] == 0x28)
        if (ydir > 0x0) ydir = 0x0;
    if (_0x12805c['which'] == 0x20) {}
}

function restartGame() {
    score = 0x0;
    deadcnt = 0x0;
    numLives = 0x3;
    bx = 0x35;
    by = 0x21;
    px = 0x30;
    py = 0x80;
    ydir = 0x0;
    fdir = 0x0;
    possessed = ![];
    autobeer = ![];
    gameover = ![];
    setState(STATE_GAME);
    tick = 0x0;
}

function setState(_0xe5eb58) {
    switch (state) {
        case STATE_INITIAL:
            break;
        case STATE_GAME:
            id = tick;
            break;
        case STATE_NAMEENTRY:
            break;
        case STATE_HIGHSCORES:
            break;
        case STATE_PROMO:
            showPromoLinks(![]);
            break;
    }
    state = _0xe5eb58;
    switch (state) {
        case STATE_INITIAL:
            break;
        case STATE_GAME:
            break;
        case STATE_NAMEENTRY:
            break;
        case STATE_HIGHSCORES:
            postingHighscore = ![];
            break;
        case STATE_PROMO:
            break;
    }
}

function update() {
    tick++;
    requestAnimationFrame(draw);
    if (!deadcnt) py += ydir * 0x2;
    if (py > 0x80) {
        py = 0x80;
        if (possessed) {
            autobeer = !![];
            possessed = ![];
            score++;
        }
    }
    if (py < 0x20) {
        py = 0x20;
        possessed = !![];
    }
    if (!deadcnt) {
        if (ydir < 0x0) {
            fdir = 0x0;
            if (possessed) {
                bx = px;
                by = py + 0x6;
            }
        }
        if (ydir > 0x0) {
            fdir = 0x1;
            if (possessed) {
                bx = px + 0x8;
                by = py + 0x6;
            }
        }
    }
    if (autobeer) {
        by = by + 0x1;
        if (by > 0xa8) {
            bx = 0x35;
            by = 0x21;
            autobeer = ![];
            possessed = ![];
        }
    }
    for (var _0x3b5146 = 0x0; _0x3b5146 < NUM_COWS; _0x3b5146++) {
        cx[_0x3b5146] += cdir[_0x3b5146] * cs[_0x3b5146];
        if (cx[_0x3b5146] < -0x40 && cdir[_0x3b5146] < 0x0) {
            cdir[_0x3b5146] = 0x1;
            cs[_0x3b5146] = Math['floor'](Math['random']() * 0x3 + 0x1);
            cx[_0x3b5146] -= Math['floor'](Math['random']() * 0x40) * 0x3;
        }
        if (cx[_0x3b5146] > 0x80 && cdir[_0x3b5146] > 0x0) {
            cdir[_0x3b5146] = -0x1;
            cs[_0x3b5146] = Math['floor'](Math['random']() * 0x3 + 0x1);
            cx[_0x3b5146] += Math['floor'](Math['random']() * 0x40) * 0x3;
        }
        if (cx[_0x3b5146] > px - 0x18 && cx[_0x3b5146] < px + 0x10) {
            if (cy[_0x3b5146] > py - 0x5 && cy[_0x3b5146] < py + 0x5) {
                if (!deadcnt) {
                    deadcnt = 0x1;
                    numLives--;
                }
            }
        }
    }
    if (deadcnt) {
        deadcnt++;
        if (deadcnt == 0x1e) {
            if (numLives == 0x0) {
                gameover = !![];
                setState(STATE_PROMO);
                checkHighscore();
            } else {
                deadcnt = 0x0;
                py = 0x80;
                by = 0x21;
                bx = 0x35;
                possessed = ![];
            }
        }
    }
}

function drawBackground() {
    ctx['drawImage'](imgBackground, 0x0, 0x0);
}

function drawScoreboard() {
    ctx['font'] = 'bold\x2036px\x20Arial';
    ctx['textAlign'] = 'center';
    ctx['fillStyle'] = '#FFFFFF';
    ctx['fillText'](score, canvas['width'] - 0x30, 0x54);
}

function drawLives() {
    for (var _0xc4335e = 0x0; _0xc4335e < numLives; _0xc4335e++) ctx['drawImage'](heart, _0xc4335e * 0x8 * SCALE + 0xc, 0xc);
}

function drawFrame(_0xb0a1b4) {
    ctx['fillStyle'] = '#000000';
    ctx['fillRect'](0x20, 0x68, canvas['width'] - 0x40, 0x128);
    ctx['fillStyle'] = '#779988';
    ctx['fillRect'](0x23, 0x6b, canvas['width'] - 0x46, 0x122);
    ctx['font'] = 'bold\x2036px\x20Arial';
    ctx['textAlign'] = 'center';
    ctx['fillStyle'] = '#FFFFFF';
    ctx['font'] = 'bold\x2018px\x20Arial';
    ctx['fillText'](_0xb0a1b4, canvas['width'] / 0x2, 0xf0);
}

function drawHighscores() {
    ctx['fillStyle'] = '#779988';
    ctx['fillRect'](0x23, 0x6b, canvas['width'] - 0x46, 0x122);
    ctx['font'] = 'bold\x2022px\x20Arial';
    ctx['fillStyle'] = '#FFFFFF';
    ctx['textAlign'] = 'center';
    ctx['strokeStyle'] = '#FFFFFF';
    ctx['lineWidth'] = 0x2;
    ctx['beginPath']();
    ctx['moveTo'](0x32, 0x91);
    ctx['lineTo'](0x136, 0x91);
    ctx['stroke']();
    ctx['fillText']('Bierholer\x20des\x20Tages', canvas['width'] / 0x2, 0x88);
    ctx['font'] = 'bold\x2018px\x20Arial';
    ctx['textAlign'] = 'left';
    for (var _0x3ffe7d = 0x0; _0x3ffe7d < 0x5; _0x3ffe7d++) ctx['fillText'](_0x3ffe7d + 0x1 + '.\x20' + highnames[_0x3ffe7d]['substring'](0x0, 0x18), 0x3c, 0xc8 + _0x3ffe7d * 0x28);
    ctx['fillStyle'] = '#668877';
    ctx['fillRect'](0x10e, 0xaa, 0x28, 0xcd);
    ctx['fillStyle'] = '#FFFFFF';
    ctx['textAlign'] = 'center';
    for (var _0x3ffe7d = 0x0; _0x3ffe7d < 0x5; _0x3ffe7d++) ctx['fillText'](highscores[_0x3ffe7d], 0x122, 0xc8 + _0x3ffe7d * 0x28);
    nameentrydiv = document['getElementById']('nameentrydiv');
    nameentrydiv['style']['display'] = 'none';
}

function drawNameEntry() {
    ctx['fillStyle'] = '#779988';
    ctx['fillRect'](0x23, 0x6b, canvas['width'] - 0x46, 0x122);
    ctx['fillStyle'] = '#FFFFFF';
    ctx['textAlign'] = 'center';
    ctx['font'] = 'bold\x2028px\x20Arial';
    ctx['fillText']('Gratulation!', canvas['width'] / 0x2, 0xa4);
    ctx['font'] = 'bold\x2018px\x20Arial';
    if (score != 0x1) ctx['fillText']('Du\x20hast\x20' + score + '\x20Bier\x20geholt', canvas['width'] / 0x2, 0xc0);
    else ctx['fillText']('Du\x20hast\x201\x20Bier\x20geholt', canvas['width'] / 0x2, 0xc0);
    ctx['fillText']('Name\x20eingeben:', canvas['width'] / 0x2, 0x118);
    nameentrydiv = document['getElementById']('nameentrydiv');
    nameentrydiv['style']['display'] = 'block';
}

function drawStartButton() {
    if (tick % 0x20 > 0x10) {
        ctx['drawImage'](start, 0x64, 0x198);
    }
}

function drawRestartButton() {
    if (tick % 0x20 > 0x10) {
        ctx['drawImage'](restart, 0x64, 0x198);
    }
}

function drawContinueButton() {
    if (tick % 0x20 > 0x10) {
        ctx['drawImage'](cont, 0x64, 0x198);
    }
}

function enterName() {
    nameentry = document['getElementById']('nameentry');
    var _0x4d0574 = prompt('Name\x20eingeben', nameentry['innerHTML']);
    _0x4d0574 = _0x4d0574['substring'](0x0, 0x18);
    if (!_0x4d0574) _0x4d0574 = '-\x20unbekannt\x20-';
    nameentry['innerHTML'] = _0x4d0574;
}

function drawBeer() {
    if (!possessed) beersprite = beerSprites[0x1];
    else beersprite = beerSprites[fdir];
    if (!(deadcnt && possessed)) ctx['drawImage'](beersprite, bx * SCALE, by * SCALE);
}

function drawPlayer() {
    if (deadcnt) {
        ctx['drawImage'](character1_dead, px * SCALE, py * SCALE);
        return;
    }
    if (ydir != 0x0) animphase = Math['floor'](tick / 0x8) % 0x4;
    else animphase = 0x0;
    sprite = characterSprites[fdir][animphase];
    ctx['drawImage'](sprite, px * SCALE, py * SCALE);
    if (possessed && fdir == 0x1 || autobeer) ctx['drawImage'](beersprite, bx * SCALE, by * SCALE);
}

function drawCows() {
    for (var _0x306b5a = 0x0; _0x306b5a < NUM_COWS; _0x306b5a++) {
        cfdir = 0x0;
        if (cdir[_0x306b5a] < 0x0) cfdir = 0x0;
        if (cdir[_0x306b5a] > 0x0) cfdir = 0x1;
        switch (_0x306b5a % 0x2) {
            case 0x0:
                animphase = Math['floor'](tick / 0x4) % 0x2 + 0x1;
                sprite = tractorSprites[Math['floor'](_0x306b5a / 0x2)][cfdir][animphase - 0x1];
                break;
            case 0x1:
                animphase = Math['floor'](tick / 0x8) % 0x2 + 0x1;
                sprite = cowSprites[cfdir][animphase];
                break;
        }
        ctx['drawImage'](sprite, cx[_0x306b5a] * SCALE, cy[_0x306b5a] * SCALE);
        if (py > cy[_0x306b5a]) drawPlayer();
    }
}

function getArrowYPos() {
    if (document['documentElement']['clientHeight'] < 0x1d1 + 0x30) return document['documentElement']['clientHeight'] - 0x30;
    return 0x1d1;
}

function drawArrows() {
    if (tick < 0x24 && tick % 0x8 < 0x4) return;
    var _0x26eec3 = getArrowYPos();
    ctx['drawImage'](arrow_up, 0x20, _0x26eec3);
    ctx['drawImage'](arrow_down, 0x100, _0x26eec3);
}

function draw() {
    drawBackground();
    drawScoreboard();
    drawLives();
    switch (state) {
        case STATE_INITIAL:
            drawFrame('Bitte\x20warten...');
            drawHighscores();
            drawStartButton();
            drawUrl();
            break;
        case STATE_GAME:
            drawBeer();
            drawPlayer();
            drawCows();
            drawArrows();
            break;
        case STATE_NAMEENTRY:
            drawFrame('Bitte\x20warten...');
            drawNameEntry();
            drawContinueButton();
            break;
        case STATE_HIGHSCORES:
            drawFrame('Bitte\x20warten...');
            drawHighscores();
            drawStartButton();
            break;
        case STATE_PROMO:
            if (waitForHighscore) {
                drawFrame('Bitte\x20warten...');
                break;
            }
            showPromoLinks(!![]);
            drawFrame('');
            drawRestartButton();
            drawUrl();
            break;
    }
}

function drawUrl() {
    ctx['font'] = 'bold\x2024px\x20Arial';
    ctx['fillText']('bier.drwuro.com', canvas['width'] / 0x2, canvas['height'] - 0x10);
}

function continueNext() {
    if (waitForHighscore || postingHighscore) return;
    if (state == STATE_NAMEENTRY) {
        var _0x30d200 = document['getElementById']('nameentry')['innerHTML'];
        if (_0x30d200 == '-\x20Dein\x20Name\x20-') return;
        postingHighscore = !![];
        var _0x314c54 = '{\x22name\x22:\x20\x22' + encodeURI(_0x30d200) + '\x22,\x20\x22score\x22:\x20' + score + ',\x20\x22id\x22:\x20' + id + '}';
        fetch('hs.php', {
            'method': 'POST',
            'body': _0x314c54
        })['then'](_0xa19f5a => _0xa19f5a['json']())['then'](_0x350464 => {
            for (var _0x1d5a24 = 0x0; _0x1d5a24 < 0x5; _0x1d5a24++) {
                highnames[_0x1d5a24] = decodeURI(_0x350464[_0x1d5a24]['name']);
                highscores[_0x1d5a24] = _0x350464[_0x1d5a24]['score'];
            }
            postingHighscore = ![];
            setState(STATE_HIGHSCORES);
        })['catch'](_0x5e63e6 => {
            alert('ERROR');
            console['log'](_0x5e63e6);
        });
    } else {
        switch (state) {
            case STATE_INITIAL:
                restartGame();
                break;
            case STATE_NAMEENTRY:
                setState(STATE_HIGHSCORES);
                break;
            case STATE_HIGHSCORES:
                restartGame();
                break;
            case STATE_PROMO:
                if (hasHighscore) setState(STATE_NAMEENTRY);
                else setState(STATE_HIGHSCORES);
                break;
        }
    }
}

function checkHighscore() {
    waitForHighscore = !![];
    fetch('hs.php')['then'](_0x4206d3 => _0x4206d3['json']())['then'](_0xea90f => {
        for (var _0x5a91cf = 0x0; _0x5a91cf < 0x5; _0x5a91cf++) {
            highnames[_0x5a91cf] = decodeURI(_0xea90f[_0x5a91cf]['name']);
            highscores[_0x5a91cf] = _0xea90f[_0x5a91cf]['score'];
        }
        if (score > _0xea90f[0x4]['score']) {
            hasHighscore = !![];
            setState(STATE_NAMEENTRY);
        } else hasHighscore = ![];
        waitForHighscore = ![];
    })['catch'](_0x2627b6 => {
        alert('ERROR');
        console['log'](_0x2627b6);
    });
}

function showPromoLinks(_0x2ad6b2) {
    if (_0x2ad6b2) {
        document['getElementById']('fblink')['style']['display'] = 'block';
        document['getElementById']('pplink')['style']['display'] = 'block';
    } else {
        document['getElementById']('fblink')['style']['display'] = 'none';
        document['getElementById']('pplink')['style']['display'] = 'none';
    }
}
setInterval(function() {
    update();
}, FRAME_TIME);
