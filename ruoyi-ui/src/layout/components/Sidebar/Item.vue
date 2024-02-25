<script>
export default {
  name: 'MenuItem',
  functional: true,
  props: {
    icon: {
      type: String,
      default: ''
    },
    title: {
      type: String,
      default: ''
    }
  },
  render(h, context) {
    const { icon, title } = context.props
    const vnodes = []

    if (icon) {
      vnodes.push(<svg-icon icon-class={icon}/>)
    }

    if (title) {
      //这边是取消官方的停留显示左侧菜单标题  参考https://blog.csdn.net/weixin_44506660/article/details/129425290
      // if (title.length > 5) {
      //   vnodes.push(<span slot='title' title={(title)}>{(title)}</span>)
      // } else {
      //   vnodes.push(<span slot='title'>{(title)}</span>)
      // }
      vnodes.push(<span slot='title'>{(title)}</span>)
    }
    return vnodes
  }
}
</script>
